package ru.ilcorp.neuro_test.utils.exeptions.user;

public class IncorrectAccessCodeException extends RuntimeException  {
    public IncorrectAccessCodeException(String message) {
        super(message);
    }
}
