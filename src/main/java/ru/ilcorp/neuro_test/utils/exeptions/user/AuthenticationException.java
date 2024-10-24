package ru.ilcorp.neuro_test.utils.exeptions.user;

public class AuthenticationException extends RuntimeException {
    public AuthenticationException(String message) {
        super(message);
    }
}
