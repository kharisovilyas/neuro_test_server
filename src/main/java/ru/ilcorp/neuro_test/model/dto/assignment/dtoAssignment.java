package ru.ilcorp.neuro_test.model.dto.assignment;

import com.fasterxml.jackson.annotation.JsonInclude;
import ru.ilcorp.neuro_test.model.dto.assignment.dtoCriteria;
import ru.ilcorp.neuro_test.model.entity.assignment.AssignmentEntity;
import ru.ilcorp.neuro_test.model.entity.assignment.criteria.CriteriaEntity;
import ru.ilcorp.neuro_test.model.entity.user.TeacherUserEntity;

import java.util.List;
@JsonInclude(JsonInclude.Include.NON_NULL)
public class dtoAssignment {
    private Long assignmentId;
    private String title;
    private String description;
    private TeacherUserEntity teacherUserEntity;
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

    public TeacherUserEntity getTeacherUserEntity() {
        return teacherUserEntity;
    }

    public void setTeacherUserEntity(TeacherUserEntity teacherUserEntity) {
        this.teacherUserEntity = teacherUserEntity;
    }

    public dtoCriteria getCriteria() {
        return criteria;
    }
}
