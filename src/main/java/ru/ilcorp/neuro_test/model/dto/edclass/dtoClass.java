package ru.ilcorp.neuro_test.model.dto.edclass;

import com.fasterxml.jackson.annotation.JsonInclude;
import ru.ilcorp.neuro_test.model.dto.user.dtoTeacherUserInformation;
import ru.ilcorp.neuro_test.model.entity.classroom.ClassEntity;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class dtoClass {
    private Long classId;
    private String className;
    private dtoTeacherUserInformation teacher;

    public dtoClass(ClassEntity classEntity) {
        this.className = classEntity.getClassName();
        this.classId = classEntity.getClassId();
    }

    public dtoClass() {
    }

    public Long getClassId() {
        return classId;
    }

    public void setClassId(Long classId) {
        this.classId = classId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public dtoTeacherUserInformation getTeacher() {
        return teacher;
    }

    public void setTeacher(dtoTeacherUserInformation teacher) {
        this.teacher = teacher;
    }
}
