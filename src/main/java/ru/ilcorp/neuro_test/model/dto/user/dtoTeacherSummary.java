package ru.ilcorp.neuro_test.model.dto.user;

import ru.ilcorp.neuro_test.model.entity.user.TeacherUserEntity;

public class dtoTeacherSummary {
    private Long teacherId;
    private String firstname;
    private String surname;

    public dtoTeacherSummary(TeacherUserEntity teacherUserEntity) {
        this.teacherId = teacherUserEntity.getUserId();
        this.firstname = teacherUserEntity.getNameOfUser();
        this.surname = teacherUserEntity.getNameOfUser();
    }

    public dtoTeacherSummary() {
    }

    public String getTeacherName() {
        return firstname;
    }

    public Long getTeacherId() {
        return teacherId;
    }

    public String getSurname() {
        return surname;
    }
}
