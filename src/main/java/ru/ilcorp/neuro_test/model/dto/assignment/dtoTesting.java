package ru.ilcorp.neuro_test.model.dto.assignment;

import com.fasterxml.jackson.annotation.JsonInclude;
import ru.ilcorp.neuro_test.model.dto.ai.dtoAssignmentResultAI;
import ru.ilcorp.neuro_test.model.dto.edclass.dtoClass;
import ru.ilcorp.neuro_test.model.dto.user.dtoTeacherSummary;
import ru.ilcorp.neuro_test.model.entity.assignment.ExtensiveTestingEntity;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
@JsonInclude(JsonInclude.Include.NON_NULL)
public class dtoTesting {
    private Long testingId;
    private String title;
    private LocalDateTime dueDate;
    private LocalDateTime createdAt;
    private Integer leadTime;
    private Integer remainingTime;
    private Boolean lateSubmission;
    private List<dtoAssignment> assignments;
    private dtoAssignmentResultAI assignmentResult;
    private dtoTeacherSummary teacher;
    private Integer quantityAssignment;
    private dtoClass edClass;

    public dtoTesting(ExtensiveTestingEntity extensiveTestingEntity) {
        this.testingId = extensiveTestingEntity.getTestingId();
        this.title = extensiveTestingEntity.getTitle();
        this.dueDate = extensiveTestingEntity.getDueDate();
        this.createdAt = extensiveTestingEntity.getCreatedAt();
        this.quantityAssignment = extensiveTestingEntity.getAssignmentEntities().size();
        this.leadTime = Duration.between(extensiveTestingEntity.getDueDate(), extensiveTestingEntity.getCreatedAt()).toMinutesPart();
        this.remainingTime = Duration.between(extensiveTestingEntity.getDueDate(), LocalDateTime.now()).toMinutesPart();
        this.assignments = extensiveTestingEntity.getAssignmentEntities().stream().map(dtoAssignment::new).collect(Collectors.toList());
        this.lateSubmission = extensiveTestingEntity.getLateSubmission();
        this.teacher = new dtoTeacherSummary(extensiveTestingEntity.getTeacherUserEntity());
    }

    public dtoTesting() {
    }

    public Long getTestingId() {
        return testingId;
    }

    public void setTestingId(Long testingId) {
        this.testingId = testingId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDateTime dueDate) {
        this.dueDate = dueDate;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public List<dtoAssignment> getAssignments() {
        return assignments;
    }

    public void setAssignments(List<dtoAssignment> assignments) {
        this.assignments = assignments;
    }

    public dtoClass getEdClass() {
        return edClass;
    }

    public void setEdClass(dtoClass edClass) {
        this.edClass = edClass;
    }

    public dtoTeacherSummary getTeacher() {
        return teacher;
    }

    public void setTeacher(dtoTeacherSummary teacher) {
        this.teacher = teacher;
    }

    public Boolean getLateSubmission() {
        return lateSubmission;
    }

    public void setLateSubmission(Boolean lateSubmission) {
        this.lateSubmission = lateSubmission;
    }

    public Integer getQuantityAssignment() {
        return quantityAssignment;
    }

    public Integer getLeadTime() {
        return leadTime;
    }

    public dtoAssignmentResultAI getAssignmentResult() {
        return assignmentResult;
    }

    public void setAssignmentResult(dtoAssignmentResultAI assignmentResult) {
        this.assignmentResult = assignmentResult;
    }

    public Integer getRemainingTime() {
        return remainingTime;
    }

    public void setRemainingTime(Integer remainingTime) {
        this.remainingTime = remainingTime;
    }
}
