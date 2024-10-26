package ru.ilcorp.neuro_test.controllers.api.v1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import ru.ilcorp.neuro_test.model.dto.user.dtoLogin;
import ru.ilcorp.neuro_test.model.dto.user.dtoStudentUserInformation;
import ru.ilcorp.neuro_test.model.dto.user.dtoTeacherUserInformation;
import ru.ilcorp.neuro_test.service.auth.AuthenticationService;
import ru.ilcorp.neuro_test.utils.components.JwtTokenProvider;
import ru.ilcorp.neuro_test.utils.exeptions.user.AuthenticationException;
import ru.ilcorp.neuro_test.utils.exeptions.user.IncorrectTokenException;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/auth/auth")
public class RestAuthController {
    @Autowired private AuthenticationService authenticationService;
    @Autowired private JwtTokenProvider jwtTokenProvider;

    @PostMapping("/refresh")
    public ResponseEntity<Map<String, String>> refreshAccessToken(@RequestBody String refreshToken) {
        try {
            jwtTokenProvider.validateRefreshToken(refreshToken); // Проверяем токен обновления
            String uniqueUsername = jwtTokenProvider.validateRefreshToken(refreshToken).getSubject();
            String newAccessToken = jwtTokenProvider.generateAccessToken(uniqueUsername); // Генерируем новый токен доступа

            Map<String, String> tokens = new HashMap<>();
            tokens.put("accessToken", newAccessToken);
            tokens.put("refreshToken", refreshToken);

            return ResponseEntity.ok(tokens);
        } catch (IncorrectTokenException e) {
            return ResponseEntity.status(401).body(null);
        }
    }

    @PostMapping("/register/student")
    public ResponseEntity<Map<String, String>> registerStudent(@RequestBody dtoStudentUserInformation student) {
        authenticationService.registerUser(student);
        return ResponseEntity.ok(authenticationService.generateToken(jwtTokenProvider, student.getEmail()));
    }

    @PostMapping("/register/teacher")
    public ResponseEntity<Map<String, String>> registerTeacher(@RequestBody dtoTeacherUserInformation teacher) {
        authenticationService.registerUser(teacher);
        return ResponseEntity.ok(authenticationService.generateToken(jwtTokenProvider, teacher.getEmail()));
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody dtoLogin user) throws AuthenticationException, IncorrectTokenException {
        // Логика аутентификации
        authenticationService.authenticate(user.getEmail(), user.getPassword());
        return ResponseEntity.ok(authenticationService.generateToken(jwtTokenProvider, user.getEmail()));
    }
}