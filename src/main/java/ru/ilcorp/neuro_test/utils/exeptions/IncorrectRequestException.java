package ru.ilcorp.neuro_test.utils.exeptions;

public class IncorrectRequestException extends RuntimeException {
    public IncorrectRequestException(String message) {
        super(message);
    }
}
