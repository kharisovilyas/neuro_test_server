package ru.ilcorp.neuro_test.model.dto.assignment;

import com.fasterxml.jackson.annotation.JsonInclude;
import ru.ilcorp.neuro_test.model.dto.user.dtoTeacherUserInformation;
import ru.ilcorp.neuro_test.model.entity.assignment.AssignmentEntity;
import ru.ilcorp.neuro_test.model.entity.user.TeacherUserEntity;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class dtoAssignment {
    private Long assignmentId;
    private String title;
    private String description;
    private dtoTeacherUserInformation teacher;
    private dtoCriteria criteria;

    public dtoAssignment(AssignmentEntity assignmentEntity) {
        this.assignmentId = assignmentEntity.getAssignmentId();
        this.description = assignmentEntity.getDescription();
        this.title = assignmentEntity.getTitle();
    }

    public dtoAssignment() {
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



    public dtoCriteria getCriteria() {
        return criteria;
    }

    public dtoTeacherUserInformation getTeacher() {
        return teacher;
    }

    public void setTeacher(dtoTeacherUserInformation teacher) {
        this.teacher = teacher;
    }

    public void setCriteria(dtoCriteria criteria) {
        this.criteria = criteria;
    }
}
