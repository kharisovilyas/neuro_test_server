package ru.ilcorp.neuro_test.service.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.ilcorp.neuro_test.model.dto.user.dtoStudentUserInformation;
import ru.ilcorp.neuro_test.model.dto.user.dtoTeacherUserInformation;
import ru.ilcorp.neuro_test.model.dto.user.dtoUser;
import ru.ilcorp.neuro_test.model.entity.classroom.ClassEntity;
import ru.ilcorp.neuro_test.model.entity.user.StudentUserEntity;
import ru.ilcorp.neuro_test.model.entity.user.TeacherUserEntity;
import ru.ilcorp.neuro_test.model.entity.user.UserAuthEntity;
import ru.ilcorp.neuro_test.repositories.classroom.edClassRepository;
import ru.ilcorp.neuro_test.repositories.user.AuthenticationRepository;
import ru.ilcorp.neuro_test.repositories.user.StudentUserRepository;
import ru.ilcorp.neuro_test.repositories.user.TeacherUserRepository;
import ru.ilcorp.neuro_test.utils.components.JwtTokenProvider;
import ru.ilcorp.neuro_test.utils.exeptions.user.AuthenticationException;
import ru.ilcorp.neuro_test.utils.exeptions.user.IncorrectAccessCodeException;
import ru.ilcorp.neuro_test.utils.exeptions.user.UserAlreadyExistsException;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Service
public class AuthenticationService {
    @Autowired
    private StudentUserRepository studentUserRepository;
    @Autowired
    private TeacherUserRepository teacherUserRepository;
    @Autowired
    private AuthenticationRepository authenticationRepository;
    @Autowired
    private edClassRepository edClassRepository;
    @Autowired
    private PasswordEncoder passwordEncoder; // Шифрование паролей
    @Autowired
    private AuthenticationManager authenticationManager;

    public Map<String, String> generateToken(JwtTokenProvider jwtTokenProvider, String uniqueUserName){
        String accessToken = jwtTokenProvider.generateAccessToken(uniqueUserName);
        String refreshToken = jwtTokenProvider.generateRefreshToken(uniqueUserName);
        Map<String, String> tokens = new HashMap<>();
        tokens.put("accessToken", accessToken);
        tokens.put("refreshToken", refreshToken);
        return tokens;
    }

    public void registerUser(dtoUser user) throws UserAlreadyExistsException {
        // Проверка существующего пользователя
        if (authenticationRepository.existsByUniqueUsername(user.getEmail())) {
            throw new UserAlreadyExistsException("Пользователь с таким email уже зарегистрирован");
        }

        Set<String> roles = new HashSet<>();
        if (user instanceof dtoStudentUserInformation) {
            roles.add("ROLE_STUDENT");
        } else if (user instanceof dtoTeacherUserInformation) {
            roles.add("ROLE_TEACHER");
        }

        //Создание
        UserAuthEntity userAuth = new UserAuthEntity(
                user.getEmail(),
                passwordEncoder.encode(user.getPassword()),
                roles
        );

        //Немедленное сохранение до сохранения сущности пользователя
        authenticationRepository.saveAndFlush(userAuth);

        // Проверяем, является ли пользователь студентом или учителем
        if (user instanceof dtoStudentUserInformation student) {
            // Обработка регистрации студента
            registerStudent(student, userAuth);
        } else if (user instanceof dtoTeacherUserInformation teacher) {
            // Обработка регистрации учителя
            registerTeacher(teacher, userAuth);
        }
    }

    private void registerStudent(dtoStudentUserInformation student, UserAuthEntity userAuthEntity) {
        ClassEntity classEntity = edClassRepository //В репозитории для таблицы Класс
                .findAll() // Находим все записи
                // Для удобства создаем поток
                .stream()
                //Выбираем только те записи в которых есть accessCode равный отправленному пользователем
                .filter(it -> it.getClassroomCode().getAccessCode().equals(student.getClassRoomCode()))
                //Выбрасываем ошибку в случае если нет такого кода доступа к уч классу
                .findAny().orElseThrow(() -> new IncorrectAccessCodeException("Некорректный код для входа в класс"));

        studentUserRepository.save(new StudentUserEntity(student, userAuthEntity, classEntity));
    }

    private void registerTeacher(dtoTeacherUserInformation teacher, UserAuthEntity userAuth) {
        teacherUserRepository.save(new TeacherUserEntity(teacher, userAuth));
    }

    public void authenticate(String email, String password) throws AuthenticationException{
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, password)
        );
        if (!auth.isAuthenticated()) {
            throw new AuthenticationException("Некорректный логин или пароль");
        }
    }

    public dtoTeacherUserInformation getTeacherInformation(String uniqueTeacherUsername) {
        return new dtoTeacherUserInformation(teacherUserRepository.findByUserAuthEntityUniqueUsername(uniqueTeacherUsername));
    }

    public dtoStudentUserInformation getStudentInformation(String uniqueStudentUsername) {
        return new dtoStudentUserInformation(studentUserRepository.findByUserAuthEntityUniqueUsername(uniqueStudentUsername));
    }
}
