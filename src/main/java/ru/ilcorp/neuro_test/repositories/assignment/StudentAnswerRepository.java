package ru.ilcorp.neuro_test.repositories.assignment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.ilcorp.neuro_test.model.entity.assignment.StudentAnswerEntity;

import java.util.List;

@Repository
public interface StudentAnswerRepository extends JpaRepository<StudentAnswerEntity, Long> {
    List<StudentAnswerEntity> findAllByStudentUserEntityUserAuthEntityUniqueUsernameAndAssignmentEntityExtensiveTestingEntityTestingId(String uniqueStudentUsername, Long testingId);
}
