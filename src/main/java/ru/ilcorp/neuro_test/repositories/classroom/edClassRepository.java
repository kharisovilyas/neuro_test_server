package ru.ilcorp.neuro_test.repositories.classroom;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ilcorp.neuro_test.model.entity.classroom.ClassEntity;

import java.util.List;

public interface edClassRepository extends JpaRepository<ClassEntity, Long> {

    List<ClassEntity> findAllByTeacherUserEntityUserAuthEntityUniqueUsername(String uniqueUsername);

}
