package ru.ilcorp.neuro_test.model.dto.response.server;

import com.fasterxml.jackson.annotation.JsonInclude;
import ru.ilcorp.neuro_test.model.dto.IDTOEntity;

import java.util.Date;
@JsonInclude(JsonInclude.Include.NON_NULL)
public class dtoToken implements IDTOEntity {
    private final String type;
    private final String token;
    private final Date serverDateTime;
    public dtoToken(String type, String token) {
        this.type = type;
        this.token = token;
        this.serverDateTime = new Date();
    }

    public String getType() {
        return type;
    }

    public String getToken() {
        return token;
    }

    public Date getServerDateTime() {
        return serverDateTime;
    }
}
