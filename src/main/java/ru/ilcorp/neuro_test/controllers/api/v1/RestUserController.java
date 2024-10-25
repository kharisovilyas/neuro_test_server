package ru.ilcorp.neuro_test.controllers.api.v1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.ilcorp.neuro_test.model.dto.user.dtoStudentUserInformation;
import ru.ilcorp.neuro_test.model.dto.user.dtoTeacherUserInformation;
import ru.ilcorp.neuro_test.service.auth.AuthenticationService;
import ru.ilcorp.neuro_test.utils.components.JwtTokenProvider;

@RestController
@RequestMapping("/api/v1/user")
public class RestUserController {
    @Autowired
    private AuthenticationService authenticationService;

    @GetMapping("/teacher")
    public ResponseEntity<dtoTeacherUserInformation> getTeacher() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String uniqueTeacherUsername = authentication.getName();
        return ResponseEntity.ok().body(authenticationService.getTeacherInformation(uniqueTeacherUsername));
    }

    @GetMapping("/student")
    public ResponseEntity<dtoStudentUserInformation> getStudent() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String uniqueStudentUsername = authentication.getName();
        return ResponseEntity.ok().body(authenticationService.getStudentInformation(uniqueStudentUsername));
    }
}
