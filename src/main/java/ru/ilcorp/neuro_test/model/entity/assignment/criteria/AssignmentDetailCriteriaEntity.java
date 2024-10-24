package ru.ilcorp.neuro_test.model.entity.assignment.criteria;

import jakarta.persistence.*;
import ru.ilcorp.neuro_test.model.entity.assignment.AssignmentEntity;
import ru.ilcorp.neuro_test.model.entity.user.TeacherUserEntity;

@Entity
@Table(name = "assignment_detail_criteria")
public class AssignmentDetailCriteriaEntity {
    @Id
    @Column(name = "id_ad_criteria")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long assignmentDetailCriteriaId;
    @ManyToOne
    @JoinColumn(name = "assignment_id")
    private AssignmentEntity assignmentEntity;
    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private TeacherUserEntity teacherUserEntity;
    @Lob
    @Column(name = "ad_criteria_description")
    private String description;
    @Column(name = "required_explained_points")
    private Integer requiredExplainedPoints;

    public AssignmentDetailCriteriaEntity() {
    }
}