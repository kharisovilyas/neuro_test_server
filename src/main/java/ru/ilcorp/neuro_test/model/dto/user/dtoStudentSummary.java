package ru.ilcorp.neuro_test.model.dto.user;

import ru.ilcorp.neuro_test.model.entity.user.StudentUserEntity;

public class dtoStudentSummary {
    private Long studentId;
    private String firstname;
    private String surname;

    public dtoStudentSummary() {
    }

    public dtoStudentSummary(StudentUserEntity studentUserEntity) {
        this.studentId = studentUserEntity.getUserId();
        this.firstname = studentUserEntity.getNameOfUser();
        this.surname = studentUserEntity.getSurnameOfUser();
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}
