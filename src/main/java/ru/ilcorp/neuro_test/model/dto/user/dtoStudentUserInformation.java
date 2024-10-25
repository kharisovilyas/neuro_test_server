package ru.ilcorp.neuro_test.model.dto.user;

import com.fasterxml.jackson.annotation.JsonInclude;
import ru.ilcorp.neuro_test.model.dto.IDTOEntity;
import ru.ilcorp.neuro_test.model.dto.classroom.dtoClass;
import ru.ilcorp.neuro_test.model.entity.user.StudentUserEntity;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class dtoStudentUserInformation extends dtoUser implements IDTOEntity, IDTOUser {
    private String classRoomCode;

    public dtoStudentUserInformation() {
    }

    public dtoStudentUserInformation(StudentUserEntity byUserAuthEntityUniqueUsername) {

    }

    public String getClassRoomCode() {
        return classRoomCode;
    }

    public void setClassRoomCode(String classRoomCode) {
        this.classRoomCode = classRoomCode;
    }
}
