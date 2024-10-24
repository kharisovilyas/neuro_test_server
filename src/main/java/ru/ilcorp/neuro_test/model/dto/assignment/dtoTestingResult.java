package ru.ilcorp.neuro_test.model.dto.assignment;

import jakarta.persistence.*;
import ru.ilcorp.neuro_test.model.dto.ai.dtoAssignmentResultAI;
import ru.ilcorp.neuro_test.model.dto.ai.dtoResponseAI;
import ru.ilcorp.neuro_test.model.entity.assignment.result.AssignmentResultEntity;
import ru.ilcorp.neuro_test.model.entity.assignment.result.ExtensiveTestingResultEntity;
import ru.ilcorp.neuro_test.model.entity.user.StudentUserEntity;
import ru.ilcorp.neuro_test.model.entity.user.TeacherUserEntity;

import java.time.LocalDateTime;
import java.util.List;

public class dtoTestingResult {
    private Long testingResultId;
    private LocalDateTime analyzeAt;
    private Double overallMark;
    private dtoResponseAI responseAI;
    private TeacherUserEntity teacherUserEntity;
    private StudentUserEntity studentUserEntity;

    public dtoTestingResult(ExtensiveTestingResultEntity testingResultEntity) {

    }
}
