package ru.ilcorp.neuro_test.controllers.api.v1;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import ru.ilcorp.neuro_test.model.dto.assignment.*;
import ru.ilcorp.neuro_test.model.dto.response.server.dtoMessage;
import ru.ilcorp.neuro_test.service.assignment.AssignmentService;
import ru.ilcorp.neuro_test.utils.exeptions.asssignment.LateSubmissionException;
import ru.ilcorp.neuro_test.utils.exeptions.user.IncorrectTokenException;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/assignment")
public class RestAssignmentController {
    @Autowired private AssignmentService assignmentService;
    @CrossOrigin(origins = {"http://localhost:3000", "http://194.58.114.242:8080", "https://ml-edu-platform.netlify.app/"})
    @PostMapping("/add")
    @PreAuthorize("hasRole('TEACHER')") // Только для пользователей с ролью учитель
    public ResponseEntity<dtoMessage> addAssignment(@RequestBody dtoTesting testing) throws EntityNotFoundException, IncorrectTokenException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String uniqueTeacherUsername = authentication.getName();
        assignmentService.addTesting(testing, uniqueTeacherUsername);
        return ResponseEntity.ok().body(new dtoMessage("SUCCESS", "Тестирование успешно загружено"));
    }

    @CrossOrigin(origins = {"http://localhost:3000", "http://194.58.114.242:8080", "https://ml-edu-platform.netlify.app/"})
    @GetMapping("/all")
    @PreAuthorize("hasRole('STUDENT')") // Только для пользователей с ролью учитель
    public ResponseEntity<List<dtoTesting>> getAllForStudent() throws EntityNotFoundException, IncorrectTokenException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String uniqueStudentUsername = authentication.getName();
        return ResponseEntity.ok().body(assignmentService.getAllForStudent(uniqueStudentUsername));
    }
    @CrossOrigin(origins = {"http://localhost:3000", "http://194.58.114.242:8080", "https://ml-edu-platform.netlify.app/"})
    @GetMapping("/all/byClass")
    @PreAuthorize("hasRole('TEACHER')") // Только для пользователей с ролью учитель
    public ResponseEntity<List<dtoTesting>> getAllByTeacher(@RequestBody Long classId) throws EntityNotFoundException, IncorrectTokenException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String uniqueTeacherUsername = authentication.getName();
        return ResponseEntity.ok().body(assignmentService.getAllForStudent(classId, uniqueTeacherUsername));
    }
    @CrossOrigin(origins = {"http://localhost:3000", "http://194.58.114.242:8080", "https://ml-edu-platform.netlify.app/"})
    @PostMapping("/answer/add")
    @PreAuthorize("hasRole('STUDENT')") // Только для пользователей с ролью студент
    public ResponseEntity<dtoMessage> uploadStudentAnswer(@RequestBody dtoStudentAnswer studentAnswer)
            throws EntityNotFoundException, IncorrectTokenException, LateSubmissionException
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String uniqueStudentUsername = authentication.getName();
        return ResponseEntity.ok().body(assignmentService.addStudentAnswer(studentAnswer, uniqueStudentUsername));
    }
    @CrossOrigin(origins = {"http://localhost:3000", "http://194.58.114.242:8080", "https://ml-edu-platform.netlify.app/"})
    @PostMapping("/answer")
    @PreAuthorize("hasRole('STUDENT')") // Только для пользователей с ролью студент
    public ResponseEntity<dtoTestingResult> uploadStudentAnswerAssignment(@RequestBody dtoTesting testing)
            throws EntityNotFoundException, IncorrectTokenException, LateSubmissionException
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String uniqueStudentUsername = authentication.getName();
        return ResponseEntity.ok().body(assignmentService.uploadStudentAnswerForAssignment(testing, uniqueStudentUsername));
    }

    @CrossOrigin(origins = {"http://localhost:3000", "http://194.58.114.242:8080", "https://ml-edu-platform.netlify.app/"})
    @PostMapping("/generic")
    @PreAuthorize("hasRole('TEACHER')") // Только для пользователей с ролью студент
    public ResponseEntity<dtoApiGIAResponse> genericAssignment()
            throws EntityNotFoundException, IncorrectTokenException, LateSubmissionException, IOException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String uniqueStudentUsername = authentication.getName();
        return ResponseEntity.ok().body(assignmentService.genericAssignment(uniqueStudentUsername));
    }
}
