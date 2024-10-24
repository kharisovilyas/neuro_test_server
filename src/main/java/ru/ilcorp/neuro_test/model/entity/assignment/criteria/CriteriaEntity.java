package ru.ilcorp.neuro_test.model.entity.assignment.criteria;

import jakarta.persistence.*;
import ru.ilcorp.neuro_test.model.dto.assignment.dtoCriteria;
import ru.ilcorp.neuro_test.model.entity.assignment.AssignmentEntity;

import java.util.Date;

@Entity
@Table(name = "criteria")
public class CriteriaEntity {
    //TODO: продумать связь с AssignmentDetailedCriteria
    @Id
    @Column(name = "id_criteria")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long criteriaId;
    @OneToOne
    @JoinColumn(name = "assignment_id")
    private AssignmentEntity assignmentEntity;
    @Lob
    @Column(name = "description")
    private String description;
    @Column(name = "weight_criteria")
    private Double weight;

    public CriteriaEntity(dtoCriteria dtoCriteria) {
        this.criteriaId = dtoCriteria.getCriteriaId();
        this.description = dtoCriteria.getDescription();
        this.weight = dtoCriteria.getWeight();
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getCriteriaId() {
        return criteriaId;
    }

    public void setCriteriaId(Long criteriaId) {
        this.criteriaId = criteriaId;
    }

    public AssignmentEntity getAssignmentEntity() {
        return assignmentEntity;
    }

    public void setAssignmentEntity(AssignmentEntity assignmentEntity) {
        this.assignmentEntity = assignmentEntity;
    }
}
