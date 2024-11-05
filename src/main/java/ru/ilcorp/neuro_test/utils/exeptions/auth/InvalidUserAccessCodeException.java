package ru.ilcorp.neuro_test.utils.exeptions.auth;

public class InvalidUserAccessCodeException extends Exception {
    public InvalidUserAccessCodeException(String message){
        super(message);
    }
}
