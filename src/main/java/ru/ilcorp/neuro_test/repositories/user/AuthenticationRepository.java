package ru.ilcorp.neuro_test.repositories.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.ilcorp.neuro_test.model.entity.user.UserAuthEntity;

import java.util.Optional;

@Repository
public interface AuthenticationRepository extends JpaRepository<UserAuthEntity, Long> {
    boolean existsByUniqueUsername(String uniqueUsername);

    Optional<UserAuthEntity> findByUniqueUsername(String uniqueUsername);
}
