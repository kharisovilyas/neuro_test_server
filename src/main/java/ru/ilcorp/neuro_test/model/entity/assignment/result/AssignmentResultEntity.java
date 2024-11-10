package ru.ilcorp.neuro_test.model.entity.assignment.result;

import jakarta.persistence.*;
import ru.ilcorp.neuro_test.model.dto.ai.dtoAssignmentResultAI;
import ru.ilcorp.neuro_test.model.entity.assignment.AssignmentEntity;
import ru.ilcorp.neuro_test.model.entity.assignment.StudentAnswerEntity;
import ru.ilcorp.neuro_test.model.entity.assignment.criteria.CriteriaEntity;

import java.util.List;

@Entity
@Table(name = "assignment_result")
public class AssignmentResultEntity {
    @Id
    @Column(name = "id_assignment_result")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long assignmentResulId;
    @ManyToOne
    @JoinColumn(name = "student_answer_id")
    private StudentAnswerEntity studentAnswerEntity;
    @ManyToOne
    @JoinColumn(name = "testing_result_id")
    private ExtensiveTestingResultEntity extensiveTestingResultEntity;
    @ManyToOne
    @JoinColumn(name = "criteria_id")
    private CriteriaEntity criteriaEntity;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "assignment_id")
    private AssignmentEntity assignmentEntity;
    @Column(name = "mark")
    private Double mark;
    @Lob
    @Column(name = "feedback")
    private String feedback;

    public AssignmentResultEntity(dtoAssignmentResultAI resultAI, AssignmentEntity assignmentEntity) {
        this.assignmentEntity = assignmentEntity;
        this.criteriaEntity = null;
        this.mark = resultAI.getMark();
        this.feedback = resultAI.getFeedback();
    }

    public Long getAssignmentResulId() {
        return assignmentResulId;
    }

    public void setAssignmentResulId(Long assignmentResulId) {
        this.assignmentResulId = assignmentResulId;
    }

    public StudentAnswerEntity getStudentAnswerEntity() {
        return studentAnswerEntity;
    }

    public void setStudentAnswerEntity(StudentAnswerEntity studentAnswerEntity) {
        this.studentAnswerEntity = studentAnswerEntity;
    }

    public CriteriaEntity getCriteriaEntity() {
        return criteriaEntity;
    }

    public void setCriteriaEntity(CriteriaEntity criteriaEntity) {
        this.criteriaEntity = criteriaEntity;
    }

    public Double getMark() {
        return mark;
    }

    public void setMark(Double mark) {
        this.mark = mark;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public ExtensiveTestingResultEntity getExtensiveTestingResultEntity() {
        return extensiveTestingResultEntity;
    }

    public void setExtensiveTestingResultEntity(ExtensiveTestingResultEntity extensiveTestingResultEntity) {
        this.extensiveTestingResultEntity = extensiveTestingResultEntity;
    }

    public AssignmentEntity getAssignmentEntity() {
        return assignmentEntity;
    }

    public void setAssignmentEntity(AssignmentEntity assignmentEntity) {
        this.assignmentEntity = assignmentEntity;
    }
}
