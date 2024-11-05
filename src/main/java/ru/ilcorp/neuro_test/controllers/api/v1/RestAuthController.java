package ru.ilcorp.neuro_test.controllers.api.v1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.ilcorp.neuro_test.model.dto.response.server.dtoMessage;
import ru.ilcorp.neuro_test.model.dto.user.dtoLogin;
import ru.ilcorp.neuro_test.model.dto.user.dtoStudentUserInformation;
import ru.ilcorp.neuro_test.model.dto.user.dtoTeacherUserInformation;
import ru.ilcorp.neuro_test.service.user.AuthenticationService;
import ru.ilcorp.neuro_test.utils.components.JwtTokenProvider;
import ru.ilcorp.neuro_test.utils.exeptions.auth.InvalidUserAccessCodeException;
import ru.ilcorp.neuro_test.utils.exeptions.user.AuthenticationException;
import ru.ilcorp.neuro_test.utils.exeptions.user.IncorrectTokenException;

import java.util.HashMap;
import java.util.Map;
@RestController
@RequestMapping("/api/v1/auth")
public class RestAuthController {
    @Autowired private AuthenticationService authenticationService;
    @Autowired private JwtTokenProvider jwtTokenProvider;

    @CrossOrigin(origins = {"http://localhost:3000", "194.58.114.242:8080", "https://ml-edu-platform.netlify.app/"})
    @RequestMapping(value = "/", method = RequestMethod.OPTIONS)
    public ResponseEntity<?> handleUserOptions() {
        return ResponseEntity.ok().build();
    }

    @CrossOrigin(origins = {"http://localhost:3000", "http://194.58.114.242:8080", "https://ml-edu-platform.netlify.app/"})
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
    @CrossOrigin(origins = {"http://localhost:3000", "http://194.58.114.242:8080", "https://ml-edu-platform.netlify.app/"})
    @PostMapping("/register/student")
    public ResponseEntity<Map<String, String>> registerStudent(@RequestBody dtoStudentUserInformation student) {
        authenticationService.registerUser(student);
        return ResponseEntity.ok(authenticationService.generateToken(jwtTokenProvider, student.getEmail()));
    }

    @CrossOrigin(origins = {"http://localhost:3000", "http://194.58.114.242:8080", "https://ml-edu-platform.netlify.app/"})
    @PostMapping("/register/teacher")
    public ResponseEntity<Map<String, String>> registerTeacher(@RequestBody dtoTeacherUserInformation teacher) {
        authenticationService.registerUser(teacher);
        return ResponseEntity.ok(authenticationService.generateToken(jwtTokenProvider, teacher.getEmail()));
    }

    @CrossOrigin(origins = {"http://localhost:3000", "http://194.58.114.242:8080", "https://ml-edu-platform.netlify.app/"})
    @GetMapping("/register/teacher")
    public ResponseEntity<dtoMessage> confirmRegistration(@RequestParam String accessCode) throws InvalidUserAccessCodeException {
        authenticationService.confirmRegistration(accessCode);
        return ResponseEntity.ok(new dtoMessage("SUCCESS", "Регистрация подтверждена! Войдите в аккаунт."));
    }

    @CrossOrigin(origins = {"http://localhost:3000", "http://194.58.114.242:8080", "https://ml-edu-platform.netlify.app/"})
    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody dtoLogin user) throws AuthenticationException, IncorrectTokenException {
        // Логика аутентификации
        authenticationService.authenticate(user.getEmail(), user.getPassword());
        return ResponseEntity.ok(authenticationService.generateToken(jwtTokenProvider, user.getEmail()));
    }
}