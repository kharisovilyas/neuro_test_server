package ru.ilcorp.neuro_test.repositories.testing;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.ilcorp.neuro_test.model.entity.assignment.ExtensiveTestingEntity;

import java.util.Arrays;
import java.util.List;

@Repository
public interface TestingRepository extends JpaRepository<ExtensiveTestingEntity, Long> {
    List<ExtensiveTestingEntity> findAllByTeacherUserEntityUserAuthEntityUniqueUsernameAndClassEntityClassId(String uniqueTeacherUsername, Long classId);

    List<ExtensiveTestingEntity> findAllByClassEntityClassId(Long classId);

    List<ExtensiveTestingEntity> findAllByTeacherUserEntityUserAuthEntityUniqueUsername(String uniqueTeacherUsername);
}
