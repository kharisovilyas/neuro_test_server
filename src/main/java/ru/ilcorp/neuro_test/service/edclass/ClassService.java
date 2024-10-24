package ru.ilcorp.neuro_test.service.edclass;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.ilcorp.neuro_test.model.dto.classroom.dtoClass;
import ru.ilcorp.neuro_test.model.entity.classroom.ClassEntity;
import ru.ilcorp.neuro_test.model.entity.user.StudentUserEntity;
import ru.ilcorp.neuro_test.model.entity.user.TeacherUserEntity;
import ru.ilcorp.neuro_test.repositories.classroom.edClassRepository;
import ru.ilcorp.neuro_test.repositories.user.StudentUserRepository;
import ru.ilcorp.neuro_test.repositories.user.TeacherUserRepository;
import ru.ilcorp.neuro_test.utils.exeptions.IncorrectRequestException;
import ru.ilcorp.neuro_test.utils.exeptions.user.IncorrectAccessCodeException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClassService {
    @Autowired
    private edClassRepository classRepository;
    @Autowired
    private TeacherUserRepository teacherUserRepository;

    @Autowired
    private StudentUserRepository studentUserRepository;

    public String createClass(dtoClass edClass, String uniqueTeacherUsername) {
        TeacherUserEntity teacherUserEntity = teacherUserRepository.findByUserAuthEntityUniqueUsername(uniqueTeacherUsername);
        ClassEntity classEntity = new ClassEntity(edClass, teacherUserEntity);
        classRepository.save(classEntity);
        return classEntity.getClassroomCode().getAccessCode();
    }

    public List<dtoClass> getAllClasses(String uniqueTeacherUsername) {
        return classRepository.findAllByTeacherUserEntityUserAuthEntityUniqueUsername(uniqueTeacherUsername).stream().map(dtoClass::new).collect(Collectors.toList());
    }

    public String updateAccessCode(dtoClass edClass) {
        if (edClass.getClassId() == null) {
            throw new IncorrectRequestException("Отправлен пустой запрос");
        }
        ClassEntity classEntity = classRepository.findById(edClass.getClassId()).orElseThrow(() -> new EntityNotFoundException("Такого класса не существует"));
        classEntity.updateClassroomCode();
        classRepository.save(classEntity);
        return classEntity.getClassroomCode().getAccessCode();
    }

    public void joinToClass(String accessCode, String uniqueStudentUsername) {
        StudentUserEntity studentUserEntity = studentUserRepository.findByUserAuthEntityUniqueUsername(uniqueStudentUsername);

        ClassEntity classEntity = classRepository //В репозитории для таблицы Класс
                .findAll() // Находим все записи
                // Для удобства создаем поток
                .stream()
                //Выбираем только те записи в которых есть accessCode равный отправленному пользователем
                .filter(it -> it.getClassroomCode().getAccessCode().equals(accessCode))
                //Выбрасываем ошибку в случае если нет такого кода доступа к уч классу
                .findAny().orElseThrow(() -> new IncorrectAccessCodeException("Некорректный код для входа в класс"));

        studentUserEntity.setClassEntity(classEntity);
        studentUserRepository.save(studentUserEntity);
    }
}
