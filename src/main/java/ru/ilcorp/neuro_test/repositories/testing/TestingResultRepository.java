package ru.ilcorp.neuro_test.repositories.testing;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.ilcorp.neuro_test.model.dto.assignment.dtoTestingResult;
import ru.ilcorp.neuro_test.model.entity.assignment.result.ExtensiveTestingResultEntity;

import java.util.List;

@Repository
public interface TestingResultRepository extends JpaRepository<ExtensiveTestingResultEntity, Long> {
    List<ExtensiveTestingResultEntity> findAllByStudentUserEntityUserAuthEntityUniqueUsername(String uniqueStudentUsername);

    ExtensiveTestingResultEntity findAllByStudentUserEntityUserAuthEntityUniqueUsernameAndTestingEntityTestingId(String uniqueStudentUsername, Long testingId);
}
