package ru.ilcorp.neuro_test.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ilcorp.neuro_test.model.dto.user.IDTOUser;
import ru.ilcorp.neuro_test.model.dto.user.dtoStudentUserInformation;
import ru.ilcorp.neuro_test.model.dto.user.dtoTeacherUserInformation;
import ru.ilcorp.neuro_test.repositories.user.StudentUserRepository;
import ru.ilcorp.neuro_test.repositories.user.TeacherUserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    private StudentUserRepository studentUserRepository;
    @Autowired
    private TeacherUserRepository teacherUserRepository;

    @Transactional
    public dtoTeacherUserInformation getTeacherInformation(String uniqueTeacherUsername) {
        return new dtoTeacherUserInformation(teacherUserRepository.findByUserAuthEntityUniqueUsername(uniqueTeacherUsername));
    }
    @Transactional
    public dtoStudentUserInformation getStudentInformation(String uniqueStudentUsername) {
        return new dtoStudentUserInformation(studentUserRepository.findByUserAuthEntityUniqueUsername(uniqueStudentUsername));
    }

    @Transactional
    public IDTOUser getUserInformation(String uniqueTeacherUsername, boolean isTeacher) {
        return isTeacher ? getTeacherInformation(uniqueTeacherUsername) : getStudentInformation(uniqueTeacherUsername);
    }

    @Transactional
    public List<dtoStudentUserInformation> getAllStudents(Long classId) {
        return studentUserRepository.findAllByClassEntityClassId(classId).stream().map(dtoStudentUserInformation::new).collect(Collectors.toList());
    }

}
