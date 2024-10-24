package ru.ilcorp.neuro_test.utils.exeptions.user;

public class IncorrectTokenException extends RuntimeException  {
    public IncorrectTokenException(String message) {
        super(message);
    }
}

