package ru.ilcorp.neuro_test.model.dto.user;

import com.fasterxml.jackson.annotation.JsonInclude;
import ru.ilcorp.neuro_test.model.dto.IDTOEntity;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class dtoLogin implements IDTOEntity {
    private String email;
    private String password;

    public dtoLogin() {
    }

    // Геттеры и сеттеры
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
