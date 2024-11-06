package ru.ilcorp.neuro_test.controllers.api.v1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import ru.ilcorp.neuro_test.model.dto.user.IDTOUser;
import ru.ilcorp.neuro_test.model.dto.user.dtoStudentUserInformation;
import ru.ilcorp.neuro_test.service.user.AuthenticationService;
import ru.ilcorp.neuro_test.service.user.UserService;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
public class RestUserController {
    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private UserService userService;

    @CrossOrigin(origins = {"http://localhost:3000", "194.58.114.242:8080", "https://ml-edu-platform.netlify.app/"})
    @GetMapping("/")
    public ResponseEntity<IDTOUser> getUserInform() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String uniqueUsername = authentication.getName();
        // Получение ролей пользователя
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

        // Пример: Проверка, есть ли у пользователя роль "ROLE_TEACHER"
        boolean isTeacher = authorities.stream()
                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_TEACHER"));

        return ResponseEntity.ok().body(userService.getUserInformation(uniqueUsername, isTeacher));
    }

    @CrossOrigin(origins = {"http://localhost:3000", "194.58.114.242:8080", "https://ml-edu-platform.netlify.app/"})
    @RequestMapping(value = "/", method = RequestMethod.OPTIONS)
    public ResponseEntity<?> handleUserOptions() {
        return ResponseEntity.ok().build();
    }

    @CrossOrigin(origins = {"http://localhost:3000", "194.58.114.242:8080", "https://ml-edu-platform.netlify.app/"})
    @PreAuthorize("hasRole('TEACHER')") // Только для пользователей с ролью учитель
    @GetMapping("/all/students")
    public ResponseEntity<List<dtoStudentUserInformation>> getAllStudentByClass(@RequestParam Long classId)
    {
        return ResponseEntity.ok().body(userService.getAllStudents(classId));
    }

}
