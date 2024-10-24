package ru.ilcorp.neuro_test.model.entity.assignment;

import jakarta.persistence.*;
import ru.ilcorp.neuro_test.model.dto.assignment.dtoStudentAnswer;
import ru.ilcorp.neuro_test.model.entity.user.StudentUserEntity;

import java.time.LocalDateTime;

@Entity
@Table(name = "student_answer")
public class StudentAnswerEntity {
    @Id
    @Column(name = "id_student_answer")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long studentAnswerId;
    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "assignment_id")
    private AssignmentEntity assignmentEntity;
    @ManyToOne
    @JoinColumn(name = "student_id")
    private StudentUserEntity studentUserEntity;
    @Column(name = "late_submission")
    private Boolean lateSubmission;
    @Lob
    @Column(name = "answer")
    private String answer;
    @Column(name = "submittedAt")
    private LocalDateTime submittedAt;
    @Column(name = "analyzed")
    private Boolean analyzed;

    public StudentAnswerEntity(dtoStudentAnswer studentAnswer, StudentUserEntity studentUserEntity, AssignmentEntity assignmentEntity, boolean lateSubmission) {
        this.assignmentEntity = assignmentEntity;
        this.studentUserEntity = studentUserEntity;
        this.answer = studentAnswer.getAnswer();
        this.submittedAt = LocalDateTime.now();
        this.analyzed = false;
        this.lateSubmission = lateSubmission;
        assignmentEntity.setStudentAnswerEntity(this);
    }

    public StudentAnswerEntity() {
    }

    public Long getStudentAnswerId() {
        return studentAnswerId;
    }

    public void setStudentAnswerId(Long studentAnswerId) {
        this.studentAnswerId = studentAnswerId;
    }

    public AssignmentEntity getAssignmentEntity() {
        return assignmentEntity;
    }

    public void setAssignmentEntity(AssignmentEntity assignmentEntity) {
        this.assignmentEntity = assignmentEntity;
    }

    public StudentUserEntity getStudentUserEntity() {
        return studentUserEntity;
    }

    public void setStudentUserEntity(StudentUserEntity studentUserEntity) {
        this.studentUserEntity = studentUserEntity;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public LocalDateTime getSubmittedAt() {
        return submittedAt;
    }

    public void setSubmittedAt(LocalDateTime submittedAt) {
        this.submittedAt = submittedAt;
    }

    public Boolean getAnalyzed() {
        return analyzed;
    }

    public void setAnalyzed(Boolean analyzed) {
        this.analyzed = analyzed;
    }

    public Boolean getLateSubmission() {
        return lateSubmission;
    }

    public void setLateSubmission(Boolean lateSubmission) {
        this.lateSubmission = lateSubmission;
    }
}
