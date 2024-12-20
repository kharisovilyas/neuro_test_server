package ru.ilcorp.neuro_test.controllers.api.v1;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import ru.ilcorp.neuro_test.model.dto.assignment.*;
import ru.ilcorp.neuro_test.model.dto.response.server.dtoMessage;
import ru.ilcorp.neuro_test.service.assignment.AssignmentService;
import ru.ilcorp.neuro_test.utils.exeptions.asssignment.LateSubmissionException;
import ru.ilcorp.neuro_test.utils.exeptions.user.IncorrectTokenException;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/api/v1/assignment")
public class RestAssignmentController {
    @Autowired private AssignmentService assignmentService;

    @CrossOrigin(origins = {"http://localhost:3000", "http://194.58.114.242:8080", "https://ml-edu-platform.netlify.app/"})
    @GetMapping("/all")
    @PreAuthorize("hasAnyRole('STUDENT', 'TEACHER')") // Только для пользователей с ролью учитель
    public ResponseEntity<List<dtoTesting>> getAll() throws EntityNotFoundException, IncorrectTokenException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String uniqueUsername = authentication.getName();
        // Получение ролей пользователя
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

        // Пример: Проверка, есть ли у пользователя роль "ROLE_TEACHER"
        boolean isTeacher = authorities.stream()
                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_TEACHER"));

        return ResponseEntity.ok().body(assignmentService.getAllForUser(isTeacher, uniqueUsername));
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
    @PostMapping("/generic")
    @PreAuthorize("hasRole('TEACHER')") // Только для пользователей с ролью студент
    public ResponseEntity<dtoApiGIAResponse> genericAssignment()
            throws EntityNotFoundException, IncorrectTokenException, LateSubmissionException, IOException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String uniqueStudentUsername = authentication.getName();
        return ResponseEntity.ok().body(assignmentService.genericAssignment(uniqueStudentUsername));
    }
}
