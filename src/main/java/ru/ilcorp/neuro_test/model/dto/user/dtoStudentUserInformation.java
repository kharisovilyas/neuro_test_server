package ru.ilcorp.neuro_test.model.dto.user;

import com.fasterxml.jackson.annotation.JsonInclude;
import ru.ilcorp.neuro_test.model.dto.IDTOEntity;
import ru.ilcorp.neuro_test.model.dto.classroom.dtoClass;
@JsonInclude(JsonInclude.Include.NON_NULL)
public class dtoStudentUserInformation extends dtoUser implements IDTOEntity {
    private String classRoomCode;
    private dtoClass edClass;

    public dtoStudentUserInformation() {
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
