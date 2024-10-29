package ru.ilcorp.neuro_test.controllers.api.v1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.ilcorp.neuro_test.model.dto.IDTOEntity;
import ru.ilcorp.neuro_test.model.dto.user.IDTOUser;
import ru.ilcorp.neuro_test.model.dto.user.dtoStudentUserInformation;
import ru.ilcorp.neuro_test.model.dto.user.dtoTeacherUserInformation;
import ru.ilcorp.neuro_test.service.auth.AuthenticationService;
import ru.ilcorp.neuro_test.utils.components.JwtTokenProvider;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
public class RestUserController {
    @Autowired
    private AuthenticationService authenticationService;

    @GetMapping("/")
    public ResponseEntity<IDTOUser> getUserInform() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String uniqueTeacherUsername = authentication.getName();
        // Получение ролей пользователя
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

        // Пример: Проверка, есть ли у пользователя роль "ROLE_TEACHER"
        boolean isTeacher = authorities.stream()
                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_TEACHER"));

        return ResponseEntity.ok().body(authenticationService.getUserInformation(uniqueTeacherUsername, isTeacher));
    }

    @GetMapping("/hello")
    @CrossOrigin(origins = "*")  // Добавьте эту аннотацию временно
    public ResponseEntity<String> helloWorld(){
        return ResponseEntity.ok("Hello World");
    }
}
