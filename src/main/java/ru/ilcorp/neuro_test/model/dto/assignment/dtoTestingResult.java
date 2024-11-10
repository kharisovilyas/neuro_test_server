package ru.ilcorp.neuro_test.model.dto.assignment;

import ru.ilcorp.neuro_test.model.dto.ai.dtoAssignmentResultAI;
import ru.ilcorp.neuro_test.model.entity.assignment.result.ExtensiveTestingResultEntity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class dtoTestingResult {
    private Long resultId;
    private LocalDateTime analyzeAt;
    private Double overallMark;
    private List<String> strengths;
    private List<String> weaknesses;
    private List<dtoAssignmentResultAI> assignmentResults;
    private List<String> poorlyStudiedTopics;
    private List<String> studiedTopics;
    private String title;
    private LocalDateTime dueDate;
    private LocalDateTime createdAt;

    public dtoTestingResult(ExtensiveTestingResultEntity testingResultEntity) {
        this.resultId = testingResultEntity.getTestingResultId();
        this.analyzeAt = testingResultEntity.getAnalyzeAt();
        this.overallMark = testingResultEntity.getOverallMark();
        this.strengths = testingResultEntity.getStrengths();
        this.weaknesses = testingResultEntity.getWeaknesses();
        this.assignmentResults = testingResultEntity.getAssignmentResultEntities().stream().map(dtoAssignmentResultAI::new).collect(Collectors.toList());
        this.poorlyStudiedTopics = testingResultEntity.getPoorlyStudiedTopics();
        this.studiedTopics = testingResultEntity.getStudiedTopics();
        this.title = testingResultEntity.getTestingEntity().getTitle();
        this.dueDate = testingResultEntity.getTestingEntity().getDueDate();
        this.createdAt = testingResultEntity.getTestingEntity().getCreatedAt();
    }

    public Long getResultId() {
        return resultId;
    }

    public void setResultId(Long resultId) {
        this.resultId = resultId;
    }

    public LocalDateTime getAnalyzeAt() {
        return analyzeAt;
    }

    public void setAnalyzeAt(LocalDateTime analyzeAt) {
        this.analyzeAt = analyzeAt;
    }

    public Double getOverallMark() {
        return overallMark;
    }

    public void setOverallMark(Double overallMark) {
        this.overallMark = overallMark;
    }

    public List<String> getStrengths() {
        return strengths;
    }

    public void setStrengths(List<String> strengths) {
        this.strengths = strengths;
    }

    public List<String> getWeaknesses() {
        return weaknesses;
    }

    public void setWeaknesses(List<String> weaknesses) {
        this.weaknesses = weaknesses;
    }

    public List<dtoAssignmentResultAI> getAssignmentResults() {
        return assignmentResults;
    }

    public void setAssignmentResults(List<dtoAssignmentResultAI> assignmentResults) {
        this.assignmentResults = assignmentResults;
    }

    public List<String> getPoorlyStudiedTopics() {
        return poorlyStudiedTopics;
    }

    public void setPoorlyStudiedTopics(List<String> poorlyStudiedTopics) {
        this.poorlyStudiedTopics = poorlyStudiedTopics;
    }

    public List<String> getStudiedTopics() {
        return studiedTopics;
    }

    public void setStudiedTopics(List<String> studiedTopics) {
        this.studiedTopics = studiedTopics;
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
}
