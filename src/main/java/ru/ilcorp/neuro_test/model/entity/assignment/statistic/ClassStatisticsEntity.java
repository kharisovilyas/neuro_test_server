package ru.ilcorp.neuro_test.model.entity.assignment.statistic;

import jakarta.persistence.*;
import ru.ilcorp.neuro_test.model.entity.classroom.ClassEntity;

import java.time.LocalDateTime;

@Entity
@Table(name = "class_statistics")
public class ClassStatisticsEntity {
    @Id
    @Column(name = "id_class_statistics")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long classStatisticsId;
    @ManyToOne
    @JoinColumn(name = "class_id")
    private ClassEntity classEntity;
    @Column(name = "average_score")
    private float averageScore;
    @Column(name = "total_assignments")
    private int totalAssignments;
    @Column(name = "total_students")
    private int totalStudents;
    @Column(name = "last_update")
    private LocalDateTime lastUpdated;

    public ClassStatisticsEntity() {
    }
}