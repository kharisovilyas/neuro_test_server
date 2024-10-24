package ru.ilcorp.neuro_test.utils.exeptions;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.ilcorp.neuro_test.utils.exeptions.user.AuthenticationException;
import ru.ilcorp.neuro_test.utils.exeptions.user.IncorrectAccessCodeException;
import ru.ilcorp.neuro_test.utils.exeptions.user.IncorrectTokenException;
import ru.ilcorp.neuro_test.utils.exeptions.user.UserAlreadyExistsException;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ApiValidationExceptionHandler {

    @ExceptionHandler(value = {
            UserAlreadyExistsException.class,
            IncorrectAccessCodeException.class,
            AuthenticationException.class,
            EntityNotFoundException.class,
            IncorrectTokenException.class
    })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    protected Map<String, Object> handleBadRequest(RuntimeException ex) {
        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("STATUS", "ERROR");
        errorResponse.put("MESSAGE", ex.getMessage());
        return errorResponse;
    }
}