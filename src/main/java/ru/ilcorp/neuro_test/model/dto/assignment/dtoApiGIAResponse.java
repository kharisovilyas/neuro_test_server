package ru.ilcorp.neuro_test.model.dto.assignment;

public class dtoApiGIAResponse {
    private String title;
    private String description;
    private String task;

    public dtoApiGIAResponse() {
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

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }
}
