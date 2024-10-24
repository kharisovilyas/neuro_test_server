package ru.ilcorp.neuro_test.repositories.assignment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.ilcorp.neuro_test.model.entity.assignment.AssignmentEntity;
import ru.ilcorp.neuro_test.model.entity.classroom.ClassEntity;

@Repository
public interface AssignmentRepository extends JpaRepository<AssignmentEntity, Long> {
}
