package ru.ilcorp.neuro_test.utils.exeptions.ai;

public class EmptyResponseFromAIException extends RuntimeException {
    public EmptyResponseFromAIException(String message) {
        super(message);
    }
}
