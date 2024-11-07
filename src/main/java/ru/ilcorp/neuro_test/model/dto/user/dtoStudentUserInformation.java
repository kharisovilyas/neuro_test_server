package ru.ilcorp.neuro_test.model.dto.user;

import com.fasterxml.jackson.annotation.JsonInclude;
import ru.ilcorp.neuro_test.model.dto.IDTOEntity;
import ru.ilcorp.neuro_test.model.dto.edclass.dtoClass;
import ru.ilcorp.neuro_test.model.entity.user.StudentUserEntity;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class dtoStudentUserInformation extends dtoUser implements IDTOEntity, IDTOUser {
    private String classRoomCode;
    private dtoClass edClass;

    public dtoStudentUserInformation() {
    }

    public dtoStudentUserInformation(StudentUserEntity studentUserEntity) {
        super.setIsTeacher(Boolean.valueOf(studentUserEntity.getTeacher()));
        super.setEmail(studentUserEntity.getEmail());
        super.setNameOfUser(studentUserEntity.getNameOfUser());
        super.setSurnameOfUser(studentUserEntity.getSurnameOfUser());
        this.edClass = (studentUserEntity.getClassEntity() == null) ?
                null : new dtoClass(studentUserEntity.getClassEntity());
    }

    public String getClassRoomCode() {
        return classRoomCode;
    }

    public void setClassRoomCode(String classRoomCode) {
        this.classRoomCode = classRoomCode;
    }

    public dtoClass getEdClass() {
        return edClass;
    }

    public void setEdClass(dtoClass edClass) {
        this.edClass = edClass;
    }
}
