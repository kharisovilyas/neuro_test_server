package ru.ilcorp.neuro_test.model.dto.edclass;

import com.fasterxml.jackson.annotation.JsonInclude;
import ru.ilcorp.neuro_test.model.dto.user.dtoStudentSummary;
import ru.ilcorp.neuro_test.model.dto.user.dtoTeacherUserInformation;
import ru.ilcorp.neuro_test.model.entity.classroom.ClassEntity;

import java.util.List;
import java.util.stream.Collectors;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class dtoClass {
    private Long classId;
    private String name;
    private String description;
    private dtoTeacherUserInformation teacher;
    private List<dtoStudentSummary> students;
    private Integer quantity;

    public dtoClass(ClassEntity classEntity) {
        this.name = classEntity.getClassName();
        this.classId = classEntity.getClassId();
        this.students = classEntity.getStudentUserEntities().stream().map(dtoStudentSummary::new).collect(Collectors.toList());
        this.description = classEntity.getClassDescription();
    }

    public dtoClass() {
    }

    public Long getClassId() {
        return classId;
    }

    public void setClassId(Long classId) {
        this.classId = classId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public dtoTeacherUserInformation getTeacher() {
        return teacher;
    }

    public void setTeacher(dtoTeacherUserInformation teacher) {
        this.teacher = teacher;
    }

    public List<dtoStudentSummary> getStudents() {
        return students;
    }

    public void setStudents(List<dtoStudentSummary> students) {
        this.students = students;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
