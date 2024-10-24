package ru.ilcorp.neuro_test.model.dto.assignment;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import ru.ilcorp.neuro_test.model.dto.user.dtoStudentUserInformation;
import ru.ilcorp.neuro_test.model.entity.assignment.AssignmentEntity;
import ru.ilcorp.neuro_test.model.entity.assignment.StudentAnswerEntity;
import ru.ilcorp.neuro_test.model.entity.user.StudentUserEntity;

import java.time.LocalDateTime;
@JsonInclude(JsonInclude.Include.NON_NULL)
public class dtoStudentAnswer {
    private Long studentAnswerId;
    private dtoAssignment assignment;
    private dtoStudentUserInformation studentUserEntity;
    private String answer;
    private Boolean lateSubmission;
    private LocalDateTime submittedAt;
    private Boolean analyzed;

    public dtoStudentAnswer() {
    }

    public Long getStudentAnswerId() {
        return studentAnswerId;
    }

    public void setStudentAnswerId(Long studentAnswerId) {
        this.studentAnswerId = studentAnswerId;
    }

    public dtoAssignment getAssignment() {
        return assignment;
    }

    public void setAssignment(dtoAssignment assignment) {
        this.assignment = assignment;
    }

    public dtoStudentUserInformation getStudentUserEntity() {
        return studentUserEntity;
    }

    public void setStudentUserEntity(dtoStudentUserInformation studentUserEntity) {
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
