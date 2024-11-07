package ru.ilcorp.neuro_test.service.assignment;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.ilcorp.neuro_test.model.dto.ai.dtoAssignmentResultAI;
import ru.ilcorp.neuro_test.model.dto.ai.dtoRequestAI;
import ru.ilcorp.neuro_test.model.dto.assignment.*;
import ru.ilcorp.neuro_test.model.dto.response.server.dtoMessage;
import ru.ilcorp.neuro_test.model.entity.assignment.AssignmentEntity;
import ru.ilcorp.neuro_test.model.entity.assignment.ExtensiveTestingEntity;
import ru.ilcorp.neuro_test.model.entity.assignment.StudentAnswerEntity;
import ru.ilcorp.neuro_test.model.dto.ai.dtoResponseAI;
import ru.ilcorp.neuro_test.model.entity.assignment.result.AssignmentResultEntity;
import ru.ilcorp.neuro_test.model.entity.assignment.result.ExtensiveTestingResultEntity;
import ru.ilcorp.neuro_test.model.entity.classroom.ClassEntity;
import ru.ilcorp.neuro_test.model.entity.user.StudentUserEntity;
import ru.ilcorp.neuro_test.model.entity.user.TeacherUserEntity;
import ru.ilcorp.neuro_test.repositories.assignment.AssignmentRepository;
import ru.ilcorp.neuro_test.repositories.assignment.StudentAnswerRepository;
import ru.ilcorp.neuro_test.repositories.assignment.TestingRepository;
import ru.ilcorp.neuro_test.repositories.classroom.edClassRepository;
import ru.ilcorp.neuro_test.repositories.user.StudentUserRepository;
import ru.ilcorp.neuro_test.repositories.user.TeacherUserRepository;
import ru.ilcorp.neuro_test.service.connect.ConnectToAIService;
import ru.ilcorp.neuro_test.utils.exeptions.asssignment.LateSubmissionException;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AssignmentService {
    @Autowired
    private AssignmentRepository assignmentRepository;
    @Autowired
    private TestingRepository testingRepository;
    @Autowired
    private TeacherUserRepository teacherUserRepository;
    @Autowired
    private edClassRepository edClassRepository;
    @Autowired
    private StudentUserRepository studentUserRepository;
    @Autowired
    private StudentAnswerRepository studentAnswerRepository;

    @Autowired
    private ConnectToAIService connectToAIService;

    @Transactional
    public void addTesting(dtoTesting testing, String uniqueTeacherUsername) throws EntityNotFoundException {
        TeacherUserEntity teacherUserEntity = teacherUserRepository.findByUserAuthEntityUniqueUsername(uniqueTeacherUsername);
        ClassEntity edClass = edClassRepository.findAllByTeacherUserEntityUserAuthEntityUniqueUsername(uniqueTeacherUsername)
                .stream()
                .filter(it -> it.getClassId().equals(testing.getEdClass().getClassId()))
                .findFirst().orElseThrow(() -> new EntityNotFoundException("Такого класса не существует"));

        List<AssignmentEntity> assignments = testing.getAssignments()
                .stream()
                .map(AssignmentEntity::new)
                .collect(Collectors.toList());

        ExtensiveTestingEntity extensiveTestingEntity = new ExtensiveTestingEntity(testing, teacherUserEntity, edClass, assignments);
        testingRepository.save(extensiveTestingEntity);
        teacherUserRepository.save(teacherUserEntity);
        edClassRepository.save(edClass);

        assignmentRepository.saveAll(assignments);
    }

    @Transactional
    public List<dtoTesting> getAllForTeacher(Long classId, String uniqueTeacherUsername) {
        return testingRepository.findAllByTeacherUserEntityUserAuthEntityUniqueUsernameAndClassEntityClassId(uniqueTeacherUsername, classId)
                .stream()
                .map(dtoTesting::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public List<dtoTesting> getAllForTeacher(String uniqueStudentUsername) {
        Long classId = studentUserRepository.findByUserAuthEntityUniqueUsername(uniqueStudentUsername).getClassEntity().getClassId();
        return testingRepository.findAllByClassEntityClassId(classId)
                .stream()
                .map(dtoTesting::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public dtoMessage addStudentAnswer(dtoStudentAnswer studentAnswer, String uniqueStudentUsername) throws EntityNotFoundException, LateSubmissionException {
        LocalDateTime timeSubmission = LocalDateTime.now();

        StudentUserEntity studentUserEntity = studentUserRepository.findByUserAuthEntityUniqueUsername(uniqueStudentUsername);

        AssignmentEntity assignmentEntity = assignmentRepository.findById(studentAnswer.getAssignment().getAssignmentId())
                .orElseThrow(() -> new EntityNotFoundException("Нет такого задания"));

        boolean lateSubmission = timeSubmission.isAfter(assignmentEntity.getExtensiveTestingEntity().getDueDate());

        if (!assignmentEntity.getExtensiveTestingEntity().getLateSubmission() && lateSubmission) {
            throw new LateSubmissionException("Задание не принято. Было сдано с опозданием");
        }
        StudentAnswerEntity studentAnswerEntity = new StudentAnswerEntity(studentAnswer, studentUserEntity, assignmentEntity, lateSubmission);
        studentAnswerRepository.save(studentAnswerEntity);
        assignmentRepository.save(assignmentEntity);
        return new dtoMessage("SUCCESS", "Ответ на задание загружен");
    }

    @Transactional
    public dtoTestingResult uploadStudentAnswerForAssignment(dtoTesting testing, String uniqueStudentUsername) {
        StudentUserEntity studentUserEntity = studentUserRepository.findByUserAuthEntityUniqueUsername(uniqueStudentUsername);
        StudentAnswerEntity studentAnswerEntity = studentAnswerRepository
                .findAllByStudentUserEntityUserAuthEntityUniqueUsernameAndAssignmentEntityExtensiveTestingEntityTestingId(
                        uniqueStudentUsername,
                        testing.getTestingId()
                )
                .stream()
                .filter(it -> it.getStudentUserEntity().equals(studentUserEntity))
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException("Ответ пользователя не найден."));

        TeacherUserEntity teacherUserEntity = teacherUserRepository
                .findById(testing.getTeacher().getTeacherId())
                .orElseThrow(() -> new EntityNotFoundException("Учителя не существует"));

        List<dtoRequestAI> requestsAI = testing.getAssignments().stream().map(it -> new dtoRequestAI(it, studentAnswerEntity)).toList();
        dtoResponseAI responseAI = connectToAIService.modellingStartFromFile(requestsAI);
        LocalDateTime analyzeAt = LocalDateTime.now();
        Double overallMark = (
                responseAI.getAssignments()
                        .stream()
                        .mapToDouble(dtoAssignmentResultAI::getMark)
                        .sum()
        ) / responseAI.getAssignments().size();

        List<AssignmentResultEntity> assignmentResultEntities = responseAI.getAssignments().stream().map(it ->
                new AssignmentResultEntity(
                        it,
                        assignmentRepository
                                .findById(it.getId())
                                .orElseThrow(() -> new EntityNotFoundException("Некорректный ответ от AI"))
                )).toList();

        ExtensiveTestingResultEntity testingResultEntity = new ExtensiveTestingResultEntity(
                assignmentResultEntities,
                responseAI,
                analyzeAt,
                overallMark,
                studentUserEntity,
                teacherUserEntity
        );
        return new dtoTestingResult(testingResultEntity);
    }

    public dtoApiGIAResponse genericAssignment(String uniqueStudentUsername) throws IOException {
        String url = "https://oge.fipi.ru/bank/index.php?proj=2F5EE3B12FE2A0EA40B06BF61A015416";
        dtoApiGIAResponse giaResponse = new dtoApiGIAResponse();

        // Подключаемся к сайту и получаем HTML-страницу
        Document doc = Jsoup.connect(url).get();

        // Ищем блок с заданием
        Element taskBlock = doc.getElementById("i4E4044");

        // Парсим необходимые элементы из блока
        if (taskBlock != null) {
            // Получение заголовка
            Element titleElement = taskBlock.selectFirst(".task-info-title > span");
            if (titleElement != null) {
                giaResponse.setTitle(titleElement.text());
            }

            // Получение описания
            Element descriptionElement = taskBlock.selectFirst(".task-info-content");
            if (descriptionElement != null) {
                giaResponse.setDescription(descriptionElement.text());
            }

            // Получение текста задания
            Element taskElement = taskBlock.selectFirst(".task-header-panel > .task");
            if (taskElement != null) {
                giaResponse.setTask(taskElement.text());
            }
        }
        return giaResponse;
    }

    @Transactional
    public List<dtoTesting> getAllForUser(boolean isTeacher, String uniqueUsername, Long classId) {
        return isTeacher ? getAllForTeacher(classId, uniqueUsername) : getAllForTeacher(uniqueUsername);
    }
}
