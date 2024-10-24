package ru.ilcorp.neuro_test.model.entity.assignment.statistic;

import jakarta.persistence.*;
import ru.ilcorp.neuro_test.model.entity.classroom.ClassEntity;
import ru.ilcorp.neuro_test.model.entity.user.StudentUserEntity;

import java.time.LocalDateTime;

@Entity
@Table(name = "student_statistics")
public class StudentStatisticsEntity {
    @Id
    @Column(name = "id_student_statistics")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long studentStatisticsId;
    @ManyToOne
    @JoinColumn(name = "student_id")
    private StudentUserEntity studentUserEntity;
    @Column(name = "average_score")
    private float averageScore;
    @Column(name = "total_assignments")
    private int totalAssignments;
    @ManyToOne
    @JoinColumn(name = "total_class_id")
    private ClassEntity classEntity;
    @Column(name = "last_update")
    private LocalDateTime lastUpdated;
    public StudentStatisticsEntity() {
    }

}