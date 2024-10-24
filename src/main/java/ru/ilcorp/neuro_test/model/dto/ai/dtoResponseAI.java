package ru.ilcorp.neuro_test.model.dto.ai;

import ru.ilcorp.neuro_test.model.dto.ai.dtoAssignmentResultAI;

import java.util.Collection;
import java.util.List;

public class dtoResponseAI {
    private List<String> strengths;
    private List<String> weaknesses;
    private List<dtoAssignmentResultAI> assignments;

    public List<String> getStrengths() {
        return strengths;
    }

    public void setStrengths(List<String> strengths) {
        this.strengths = strengths;
    }

    public List<String> getWeaknesses() {
        return weaknesses;
    }

    public void setWeaknesses(List<String> weaknesses) {
        this.weaknesses = weaknesses;
    }

    public List<dtoAssignmentResultAI> getAssignments() {
        return assignments;
    }

    public void setAssignments(List<dtoAssignmentResultAI> assignments) {
        this.assignments = assignments;
    }
}
