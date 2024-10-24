package ru.ilcorp.neuro_test.model.entity.classroom;

import jakarta.persistence.Embeddable;
import ru.ilcorp.neuro_test.utils.generic.CodeGeneric;

import java.time.LocalDateTime;

@Embeddable
public class ClassroomCode {
    private String accessCode;
    private LocalDateTime createAt;

    public ClassroomCode() {
        this.accessCode = CodeGeneric.generateAccessCode();
        this.createAt = LocalDateTime.now();
    }

    public String getAccessCode() {
        return accessCode;
    }

    public void setAccessCode(String accessCode) {
        this.accessCode = accessCode;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }
}
