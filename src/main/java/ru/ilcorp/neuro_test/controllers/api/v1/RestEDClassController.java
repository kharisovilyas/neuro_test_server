package ru.ilcorp.neuro_test.controllers.api.v1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.ilcorp.neuro_test.model.dto.classroom.dtoClass;
import ru.ilcorp.neuro_test.model.dto.response.server.dtoMessage;
import ru.ilcorp.neuro_test.service.edclass.ClassService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import ru.ilcorp.neuro_test.utils.exeptions.user.IncorrectTokenException;

import java.util.List;

@Controller
@RequestMapping("/api/v1/class")
public class RestEDClassController {
    @Autowired
    private ClassService classService;

    @PostMapping("/add")
    @PreAuthorize("hasRole('TEACHER')") // Только для пользователей с ролью учитель
    public ResponseEntity<String> createClass(@RequestBody dtoClass classDto)
            throws IncorrectTokenException
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String uniqueTeacherUsername = authentication.getName();
        return ResponseEntity.ok().body(classService.createClass(classDto, uniqueTeacherUsername));
    }

    @GetMapping("/update/code")
    @PreAuthorize("hasRole('TEACHER')") // Только для пользователей с ролью учитель
    public ResponseEntity<String> updateClassAccessCode(@RequestBody dtoClass classDto)
            throws IncorrectTokenException
    {
        return ResponseEntity.ok().body(classService.updateAccessCode(classDto));
    }

    @GetMapping("/get/all")
    @PreAuthorize("hasRole('TEACHER')") // Только для пользователей с ролью учитель
    public ResponseEntity<List<dtoClass>> getAllClasses()
            throws IncorrectTokenException
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String uniqueTeacherUsername = authentication.getName();
        return ResponseEntity.ok().body(classService.getAllClasses(uniqueTeacherUsername));
    }

    @PostMapping("/join")
    @PreAuthorize("hasRole('STUDENT')") // Только для пользователей с ролью студент
    public ResponseEntity<dtoMessage> joinToClass(@RequestParam String accessCode)
            throws IncorrectTokenException
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String uniqueStudentUsername = authentication.getName();
        classService.joinToClass(accessCode, uniqueStudentUsername);
        return ResponseEntity.ok().body(new dtoMessage("SUCCESS", "Присоединение к классу прошло успешно"));
    }
}
