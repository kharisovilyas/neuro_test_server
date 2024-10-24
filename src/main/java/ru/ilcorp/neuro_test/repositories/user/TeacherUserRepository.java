package ru.ilcorp.neuro_test.repositories.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.ilcorp.neuro_test.model.entity.user.TeacherUserEntity;
import ru.ilcorp.neuro_test.model.entity.user.UserAuthEntity;
@Repository
public interface TeacherUserRepository extends JpaRepository<TeacherUserEntity, Long> {
    TeacherUserEntity findByUserAuthEntityUniqueUsername(String uniqueTeacherUsername);
}
