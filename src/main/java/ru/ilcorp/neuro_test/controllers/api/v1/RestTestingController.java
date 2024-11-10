package ru.ilcorp.neuro_test.controllers.api.v1;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import ru.ilcorp.neuro_test.model.dto.assignment.dtoTesting;
import ru.ilcorp.neuro_test.model.dto.assignment.dtoTestingResult;
import ru.ilcorp.neuro_test.model.dto.response.server.dtoMessage;
import ru.ilcorp.neuro_test.service.assignment.AssignmentService;
import ru.ilcorp.neuro_test.utils.exeptions.asssignment.LateSubmissionException;
import ru.ilcorp.neuro_test.utils.exeptions.user.IncorrectTokenException;

@RestController
@RequestMapping("/api/v1/testing")
public class RestTestingController {
    @Autowired
    private AssignmentService assignmentService;

    @CrossOrigin(origins = {"http://localhost:3000", "http://194.58.114.242:8080", "https://ml-edu-platform.netlify.app/"})
    @PostMapping("/add")
    @PreAuthorize("hasRole('TEACHER')") // Только для пользователей с ролью учитель
    public ResponseEntity<dtoMessage> addAssignment(@RequestBody dtoTesting testing)
            throws EntityNotFoundException, IncorrectTokenException
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String uniqueTeacherUsername = authentication.getName();
        assignmentService.addTesting(testing, uniqueTeacherUsername);
        return ResponseEntity.ok().body(new dtoMessage("SUCCESS", "Тестирование успешно загружено"));
    }

    @CrossOrigin(origins = {"http://localhost:3000", "http://194.58.114.242:8080", "https://ml-edu-platform.netlify.app/"})
    @PostMapping("/answer")
    @PreAuthorize("hasRole('STUDENT')") // Только для пользователей с ролью студент
    public ResponseEntity<dtoTestingResult> uploadStudentAnswerForTesting(@RequestBody dtoTesting testing)
            throws EntityNotFoundException, IncorrectTokenException, LateSubmissionException
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String uniqueStudentUsername = authentication.getName();
        return ResponseEntity.ok().body(assignmentService.uploadStudentAnswerForTesting(testing, uniqueStudentUsername));
    }

    @CrossOrigin(origins = {"http://localhost:3000", "http://194.58.114.242:8080", "https://ml-edu-platform.netlify.app/"})
    @PostMapping("/result")
    @PreAuthorize("hasRole('STUDENT')") // Только для пользователей с ролью студент
    public ResponseEntity<dtoTestingResult> getAnalyzerTesting(@RequestParam Long testingId)
            throws EntityNotFoundException, IncorrectTokenException, LateSubmissionException
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String uniqueStudentUsername = authentication.getName();
        return ResponseEntity.ok().body(assignmentService.getResultForStudent(testingId, uniqueStudentUsername));
    }
}
