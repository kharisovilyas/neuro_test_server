package ru.ilcorp.neuro_test.model.dto.assignment;

import ru.ilcorp.neuro_test.model.dto.ai.dtoResponseAI;
import ru.ilcorp.neuro_test.model.entity.assignment.result.ExtensiveTestingResultEntity;
import ru.ilcorp.neuro_test.model.entity.user.StudentUserEntity;
import ru.ilcorp.neuro_test.model.entity.user.TeacherUserEntity;

import java.time.LocalDateTime;

public class dtoTestingResult {
    private Long resultId;
    private LocalDateTime analyzeAt;
    private Double overallMark;
    private dtoResponseAI responseAI;
    private TeacherUserEntity teacherUserEntity;
    private StudentUserEntity studentUserEntity;

    public dtoTestingResult(ExtensiveTestingResultEntity testingResultEntity) {

    }
}
