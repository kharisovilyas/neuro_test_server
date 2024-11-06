package ru.ilcorp.neuro_test.controllers.api.v1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.ilcorp.neuro_test.model.dto.edclass.dtoClass;
import ru.ilcorp.neuro_test.model.dto.response.server.dtoMessage;
import ru.ilcorp.neuro_test.service.edclass.ClassService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import ru.ilcorp.neuro_test.utils.exeptions.user.IncorrectTokenException;

import java.util.List;

@RestController
@RequestMapping("/api/v1/class")
public class RestEDClassController {
    @Autowired
    private ClassService classService;

    @CrossOrigin(origins = {"http://localhost:3000", "http://194.58.114.242:8080", "https://ml-edu-platform.netlify.app/"})
    @PostMapping("/add")
    @PreAuthorize("hasRole('TEACHER')") // Только для пользователей с ролью учитель
    public ResponseEntity<String> createClass(@RequestBody dtoClass classDto)
            throws IncorrectTokenException
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String uniqueTeacherUsername = authentication.getName();
        return ResponseEntity.ok().body(classService.createClass(classDto, uniqueTeacherUsername));
    }

    @CrossOrigin(origins = {"http://localhost:3000", "http://194.58.114.242:8080", "https://ml-edu-platform.netlify.app/"})
    @GetMapping("/update/code")
    @PreAuthorize("hasRole('TEACHER')") // Только для пользователей с ролью учитель
    public ResponseEntity<String> updateClassAccessCode(@RequestParam Long classId)
            throws IncorrectTokenException
    {
        return ResponseEntity.ok().body(classService.updateAccessCode(classId));
    }

    @CrossOrigin(origins = {"http://localhost:3000", "http://194.58.114.242:8080", "https://ml-edu-platform.netlify.app/"})
    @GetMapping("/send/code")
    @PreAuthorize("hasRole('TEACHER')") // Только для пользователей с ролью учитель
    public ResponseEntity<String> sendAccessCodeForStudents(@RequestBody Long classId)
            throws IncorrectTokenException
    {
        return ResponseEntity.ok().body(classService.sendUpdateAccessCode(classId));
    }

    @CrossOrigin(origins = {"http://localhost:3000", "http://194.58.114.242:8080", "https://ml-edu-platform.netlify.app/"})
    @GetMapping("/get/all")
    @PreAuthorize("hasRole('TEACHER')") // Только для пользователей с ролью учитель
    public ResponseEntity<List<dtoClass>> getAllClasses()
            throws IncorrectTokenException
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String uniqueTeacherUsername = authentication.getName();
        return ResponseEntity.ok().body(classService.getAllClasses(uniqueTeacherUsername));
    }

    @CrossOrigin(origins = {"http://localhost:3000", "http://194.58.114.242:8080", "https://ml-edu-platform.netlify.app/"})
    @PostMapping("/join")
    @PreAuthorize("hasRole('STUDENT')") // Только для пользователей с ролью студент
    public ResponseEntity<dtoMessage> joinToClass(@RequestBody String accessCode)
            throws IncorrectTokenException
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String uniqueStudentUsername = authentication.getName();
        classService.joinToClass(accessCode, uniqueStudentUsername);
        return ResponseEntity.ok().body(new dtoMessage("SUCCESS", "Присоединение к классу прошло успешно"));
    }
}
