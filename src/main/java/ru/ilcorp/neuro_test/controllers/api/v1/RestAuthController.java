package ru.ilcorp.neuro_test.controllers.api.v1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.ilcorp.neuro_test.model.dto.response.server.dtoToken;
import ru.ilcorp.neuro_test.model.dto.user.dtoLogin;
import ru.ilcorp.neuro_test.model.dto.user.dtoStudentUserInformation;
import ru.ilcorp.neuro_test.model.dto.user.dtoTeacherUserInformation;
import ru.ilcorp.neuro_test.service.auth.AuthenticationService;
import ru.ilcorp.neuro_test.utils.components.JwtTokenProvider;
import ru.ilcorp.neuro_test.utils.exeptions.user.AuthenticationException;
import ru.ilcorp.neuro_test.utils.exeptions.user.IncorrectTokenException;

@Controller
@RequestMapping("/api/v1/auth")
public class RestAuthController {
    @Autowired private AuthenticationService authenticationService;
    @Autowired private JwtTokenProvider jwtTokenProvider;


    @PostMapping("/register/student")
    public ResponseEntity<String> registerStudent(@RequestBody dtoStudentUserInformation student) {
        authenticationService.registerUser(student);
        return ResponseEntity.ok().body(jwtTokenProvider.generateToken(student.getEmail()));
    }

    @PostMapping("/register/teacher")
    public ResponseEntity<String> registerTeacher(@RequestBody dtoTeacherUserInformation teacher) {
        authenticationService.registerUser(teacher);
        return ResponseEntity.ok().body(jwtTokenProvider.generateToken(teacher.getEmail()));
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody dtoLogin user) throws AuthenticationException, IncorrectTokenException {
        // Логика аутентификации
        authenticationService.authenticate(user.getEmail(), user.getPassword());
        return ResponseEntity.ok(jwtTokenProvider.generateToken(user.getEmail()));
    }
}