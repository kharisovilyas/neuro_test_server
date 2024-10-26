package ru.ilcorp.neuro_test.model.dto.user;

import com.fasterxml.jackson.annotation.JsonInclude;
import ru.ilcorp.neuro_test.model.dto.IDTOEntity;
import ru.ilcorp.neuro_test.model.dto.assignment.dtoTesting;
import ru.ilcorp.neuro_test.model.dto.classroom.dtoClass;
import ru.ilcorp.neuro_test.model.entity.user.TeacherUserEntity;

import java.util.List;
import java.util.stream.Collectors;
@JsonInclude(JsonInclude.Include.NON_NULL)
public class dtoTeacherUserInformation extends dtoUser implements IDTOEntity, IDTOUser {
    private List<dtoClass> edClasses;
    private List<dtoTesting> testings;

    public dtoTeacherUserInformation(TeacherUserEntity teacherUserEntity) {
        this.edClasses = teacherUserEntity.getClassEntities().stream().map(dtoClass::new).collect(Collectors.toList());
        this.testings = teacherUserEntity.getTestingEntities().stream().map(dtoTesting::new).collect(Collectors.toList());
        super.setIsTeacher(teacherUserEntity.getTeacher());
        super.setEmail(teacherUserEntity.getEmail());
        super.setNameOfUser(teacherUserEntity.getNameOfUser());
        super.setSurnameOfUser(teacherUserEntity.getSurnameOfUser());
    }

    public dtoTeacherUserInformation() {
    }

    public List<dtoClass> getEdClasses() {
        return edClasses;
    }

    public void setEdClasses(List<dtoClass> edClasses) {
        this.edClasses = edClasses;
    }

    public List<dtoTesting> getTestings() {
        return testings;
    }

    public void setTestings(List<dtoTesting> testings) {
        this.testings = testings;
    }
}
