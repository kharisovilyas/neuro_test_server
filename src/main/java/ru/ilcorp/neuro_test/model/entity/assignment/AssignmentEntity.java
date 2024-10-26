package ru.ilcorp.neuro_test.model.entity.assignment;

import jakarta.persistence.*;
import ru.ilcorp.neuro_test.model.dto.ai.dtoAssignmentResultAI;
import ru.ilcorp.neuro_test.model.dto.assignment.dtoAssignment;
import ru.ilcorp.neuro_test.model.entity.assignment.criteria.CriteriaEntity;
import ru.ilcorp.neuro_test.model.entity.assignment.result.AssignmentResultEntity;
import ru.ilcorp.neuro_test.model.dto.assignment.dtoCriteria;
import ru.ilcorp.neuro_test.model.entity.assignment.criteria.CriteriaEntity;

import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "assignment")
public class AssignmentEntity {
    @Id
    @Column(name = "id_assignment")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long assignmentId;
    @Column(name = "title")
    private String title;
    @Lob
    @Column(name = "description")
    private String description;
    @Lob
    @Column(name = "task")
    private String task;
    @OneToOne(mappedBy = "assignmentEntity")
    private CriteriaEntity criteriaEntity;
    @ManyToOne
    @JoinColumn(name = "testing_id")
    private ExtensiveTestingEntity extensiveTestingEntity;
    @OneToOne(mappedBy = "assignmentEntity", cascade = CascadeType.ALL)
    private StudentAnswerEntity studentAnswerEntity;
    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "assignment_result_id")
    private AssignmentResultEntity assignmentEntity;

    public AssignmentEntity() {
    }

    public AssignmentEntity(dtoAssignment dtoAssignment) {
        this.title = dtoAssignment.getTitle();
        this.description = dtoAssignment.getDescription();
        this.task = dtoAssignment.getTask();
    }

    public Long getAssignmentId() {
        return assignmentId;
    }

    public void setAssignmentId(Long assignmentId) {
        this.assignmentId = assignmentId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public CriteriaEntity getCriteriaEntities() {
        return criteriaEntity;
    }

    public void setCriteriaEntities(CriteriaEntity criteriaEntities) {
        this.criteriaEntity = criteriaEntities;
    }

    public ExtensiveTestingEntity getExtensiveTestingEntity() {
        return extensiveTestingEntity;
    }

    public void setExtensiveTestingEntity(ExtensiveTestingEntity extensiveTestingEntity) {
        this.extensiveTestingEntity = extensiveTestingEntity;
    }

    public StudentAnswerEntity getStudentAnswerEntity() {
        return studentAnswerEntity;
    }

    public void setStudentAnswerEntity(StudentAnswerEntity studentAnswerEntity) {
        this.studentAnswerEntity = studentAnswerEntity;
    }

    public AssignmentResultEntity getAssignmentEntity() {
        return assignmentEntity;
    }

    public void setAssignmentEntity(AssignmentResultEntity assignmentEntity) {
        this.assignmentEntity = assignmentEntity;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }
}