package ru.ilcorp.neuro_test.utils.exeptions.asssignment;

public class LateSubmissionException extends RuntimeException {
    public LateSubmissionException(String message) {
        super(message);
    }
}
