package ru.ilcorp.neuro_test.repositories.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.ilcorp.neuro_test.model.entity.user.StudentUserEntity;
import ru.ilcorp.neuro_test.model.entity.user.UserAuthEntity;

import java.util.Arrays;
import java.util.List;

@Repository
public interface StudentUserRepository extends JpaRepository<StudentUserEntity, Long>{
    StudentUserEntity findByUserAuthEntityUniqueUsername(String uniqueStudentUsername);

    List<StudentUserEntity> findAllByClassEntityClassId(Long classId);
}
