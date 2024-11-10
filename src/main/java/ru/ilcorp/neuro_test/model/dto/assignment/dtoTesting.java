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
    private dtoTestingResult testingResult;
    private dtoTeacherSummary teacher;
    private Integer quantityAssignment;
    private dtoClass edClass;
    private Boolean isAnalyzer;

    public dtoTesting(ExtensiveTestingEntity extensiveTestingEntity) {
        this.testingId = extensiveTestingEntity.getTestingId();
        this.title = extensiveTestingEntity.getTitle();
        this.dueDate = extensiveTestingEntity.getDueDate();
        this.createdAt = extensiveTestingEntity.getCreatedAt();
        this.quantityAssignment = extensiveTestingEntity.getAssignmentEntities().size();
        this.leadTime = Duration.between(extensiveTestingEntity.getCreatedAt(), extensiveTestingEntity.getDueDate()).toMinutesPart();
        this.remainingTime = Duration.between(LocalDateTime.now(), extensiveTestingEntity.getDueDate()).toMinutesPart();
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

    public void setLeadTime(Integer leadTime) {
        this.leadTime = leadTime;
    }

    public dtoTestingResult getTestingResult() {
        return testingResult;
    }

    public void setTestingResult(dtoTestingResult testingResult) {
        this.testingResult = testingResult;
    }

    public void setQuantityAssignment(Integer quantityAssignment) {
        this.quantityAssignment = quantityAssignment;
    }

    public Integer getRemainingTime() {
        return remainingTime;
    }

    public void setRemainingTime(Integer remainingTime) {
        this.remainingTime = remainingTime;
    }
}
