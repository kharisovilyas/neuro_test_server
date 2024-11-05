package ru.ilcorp.neuro_test.service.user;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.ilcorp.neuro_test.model.dto.user.IDTOUser;
import ru.ilcorp.neuro_test.model.dto.user.dtoStudentUserInformation;
import ru.ilcorp.neuro_test.model.dto.user.dtoTeacherUserInformation;
import ru.ilcorp.neuro_test.model.dto.user.dtoUser;
import ru.ilcorp.neuro_test.model.entity.classroom.ClassEntity;
import ru.ilcorp.neuro_test.model.entity.user.StudentUserEntity;
import ru.ilcorp.neuro_test.model.entity.user.TeacherUserEntity;
import ru.ilcorp.neuro_test.model.entity.user.UserAccessCode;
import ru.ilcorp.neuro_test.model.entity.user.UserAuthEntity;
import ru.ilcorp.neuro_test.repositories.classroom.edClassRepository;
import ru.ilcorp.neuro_test.repositories.user.AuthenticationRepository;
import ru.ilcorp.neuro_test.repositories.user.StudentUserRepository;
import ru.ilcorp.neuro_test.repositories.user.TeacherUserRepository;
import ru.ilcorp.neuro_test.utils.components.JwtTokenProvider;
import ru.ilcorp.neuro_test.utils.exeptions.auth.InvalidUserAccessCodeException;
import ru.ilcorp.neuro_test.utils.exeptions.user.AuthenticationException;
import ru.ilcorp.neuro_test.utils.exeptions.user.UserAlreadyExistsException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.SimpleMailMessage;

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
    @Autowired private JavaMailSender mailSender; // Инжектируйте JavaMailSender
    @Value("${web.app.url}")
    private String webAppUrl;
    @Transactional
    public Map<String, String> generateToken(JwtTokenProvider jwtTokenProvider, String uniqueUserName){
        String accessToken = jwtTokenProvider.generateAccessToken(uniqueUserName);
        String refreshToken = jwtTokenProvider.generateRefreshToken(uniqueUserName);
        Map<String, String> tokens = new HashMap<>();
        tokens.put("accessToken", accessToken);
        tokens.put("refreshToken", refreshToken);
        return tokens;
    }
    @Transactional
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

        //TODO: доделать
        //   sendConfirmationEmail(user.getEmail());
    }

    @Transactional
    private void sendConfirmationEmail(String email) {
        UserAccessCode userAccessCode = new UserAccessCode();
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Подтверждение регистрации Write Grade");
        message.setText("Для подтверждения регистрации перейдите по ссылке:\n" +
                webAppUrl + "/confirm-registration?token=" + userAccessCode.getAccessCode());
        mailSender.send(message);
    }

    @Transactional
    public void confirmRegistration(String accessCode) throws InvalidUserAccessCodeException {
        UserAuthEntity userAuthEntity = authenticationRepository.findAll() // Находим все записи
                // Для удобства создаем поток
                .stream()
                //Выбираем только те записи в которых есть accessCode равный отправленному пользователем
                .filter(it -> it.getUserAccessCode().getAccessCode().equals(accessCode))
                //Выбрасываем ошибку в случае если нет такого кода доступа к уч классу
                .findAny().orElseThrow(() -> new InvalidUserAccessCodeException("Некорректная ссылка"));
        userAuthEntity.setConfirmEmail(true);
        authenticationRepository.save(userAuthEntity);
    }

    @Transactional
    private void registerStudent(dtoStudentUserInformation student, UserAuthEntity userAuthEntity) {
        ClassEntity classEntity = edClassRepository //В репозитории для таблицы Класс
                .findAll() // Находим все записи
                // Для удобства создаем поток
                .stream()
                //Выбираем только те записи в которых есть accessCode равный отправленному пользователем
                .filter(it -> it.getClassroomCode().getAccessCode().equals(student.getClassRoomCode()))
                //Выбрасываем ошибку в случае если нет такого кода доступа к уч классу
                .findAny().orElse(null);

        studentUserRepository.save(new StudentUserEntity(student, userAuthEntity, classEntity));
    }
    @Transactional
    private void registerTeacher(dtoTeacherUserInformation teacher, UserAuthEntity userAuth) {
        teacherUserRepository.save(new TeacherUserEntity(teacher, userAuth));
    }
    @Transactional
    public void authenticate(String email, String password) throws AuthenticationException{
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, password)
        );
        if (!auth.isAuthenticated()) {
            throw new AuthenticationException("Некорректный логин или пароль");
        }
    }
}
