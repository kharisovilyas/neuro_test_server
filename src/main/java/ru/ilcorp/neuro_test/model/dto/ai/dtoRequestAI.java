package ru.ilcorp.neuro_test.model.dto.ai;

import ru.ilcorp.neuro_test.model.dto.assignment.dtoAssignment;
import ru.ilcorp.neuro_test.model.dto.assignment.dtoCriteria;
import ru.ilcorp.neuro_test.model.dto.assignment.dtoStudentAnswer;
import ru.ilcorp.neuro_test.model.entity.assignment.StudentAnswerEntity;

import java.util.List;

public class dtoRequestAI {
    private String title;
    private String description;
    private String task;
    private String answer;
    private dtoCriteria criteria;
    public dtoRequestAI(dtoAssignment assignment, StudentAnswerEntity studentAnswerEntity) {
        this.title = assignment.getTitle();
        this.description = assignment.getDescription();
        this.answer = studentAnswerEntity.getAnswer();
        this.task = assignment.getTask();
        this.criteria = assignment.getCriteria();
    }

    public dtoCriteria getCriteria() {
        return criteria;
    }

    public void setCriteria(dtoCriteria criteria) {
        this.criteria = criteria;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }
}
