package ru.ilcorp.neuro_test.model.dto.ai;

import ru.ilcorp.neuro_test.model.dto.assignment.dtoAssignment;
import ru.ilcorp.neuro_test.model.entity.assignment.result.AssignmentResultEntity;

public class dtoAssignmentResultAI {
    private Long id;
    private Double mark;
    private String feedback;
    private dtoAssignment assignment;

    public dtoAssignmentResultAI(AssignmentResultEntity assignmentResult) {
        this.id = assignmentResult.getAssignmentResulId();
        this.mark = assignmentResult.getMark();
        this.feedback = assignmentResult.getFeedback();
        this.assignment = new dtoAssignment(assignmentResult.getAssignmentEntity());
    }

    public dtoAssignmentResultAI() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public dtoAssignment getAssignment() {
        return assignment;
    }

    public void setAssignment(dtoAssignment assignment) {
        this.assignment = assignment;
    }
}
