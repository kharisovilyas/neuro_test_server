package ru.ilcorp.neuro_test.model.entity.assignment.result;

import jakarta.persistence.*;
import ru.ilcorp.neuro_test.model.entity.assignment.AssignmentEntity;
import ru.ilcorp.neuro_test.model.entity.classroom.ClassEntity;
import ru.ilcorp.neuro_test.model.entity.user.StudentUserEntity;
import ru.ilcorp.neuro_test.model.entity.user.TeacherUserEntity;

import java.time.LocalDateTime;

@Entity
@Table(name = "class_process")
public class ClassProgressEntity {
    @Id
    @Column(name = "id_class_progress")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long classProgressId;

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private TeacherUserEntity teacherUserEntity;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private StudentUserEntity studentUserEntity;

    @ManyToOne
    @JoinColumn(name = "assignment_id")
    private AssignmentEntity assignmentEntity;

    @ManyToOne
    @JoinColumn(name = "class_id")
    private ClassEntity classEntity;

    @Column(name = "total_score")
    private Double totalScore;

    @Lob
    @Column(name = "progressSummary")
    private String progressSummary;

    @Column(name = "update_at")
    private LocalDateTime updatedAt;

    public ClassProgressEntity() {
    }
}
