package ru.ilcorp.neuro_test.model.entity.assignment.result;

import jakarta.persistence.*;
import ru.ilcorp.neuro_test.model.dto.ai.dtoResponseAI;
import ru.ilcorp.neuro_test.model.entity.assignment.ExtensiveTestingEntity;
import ru.ilcorp.neuro_test.model.entity.user.StudentUserEntity;
import ru.ilcorp.neuro_test.model.entity.user.TeacherUserEntity;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "testing_result")
public class ExtensiveTestingResultEntity {
    @Id
    @Column(name = "id_testing_result")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long testingResultId;
    @Column(name = "analyze_at")
    private LocalDateTime analyzeAt;
    @Column(name = "overall_mark")
    private Double overallMark;
    @Column(name = "poorly_studied_topics")
    private List<String> poorlyStudiedTopics;
    @Column(name = "studied_topics")
    private List<String> studiedTopics;
    @Column(name = "strengths")
    private List<String> strengths;
    @Column(name = "weaknesses")
    private List<String> weaknesses;
    @OneToMany(mappedBy = "extensiveTestingResultEntity")
    private List<AssignmentResultEntity> assignmentResultEntities;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "teacher_id")
    private TeacherUserEntity teacherUserEntity;
    @ManyToOne
    @JoinColumn(name = "student_id")
    private StudentUserEntity studentUserEntity;
    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "testing")
    private ExtensiveTestingEntity testingEntity;

    public ExtensiveTestingResultEntity(
            ExtensiveTestingEntity testing,
            List<AssignmentResultEntity> assignmentResultEntities,
            dtoResponseAI responseAI,
            LocalDateTime analyzeAt,
            Double overallMark,
            StudentUserEntity studentUserEntity,
            TeacherUserEntity teacherUserEntity
    ) {
        this.analyzeAt = analyzeAt;
        this.assignmentResultEntities = assignmentResultEntities;
        this.overallMark = overallMark;
        this.strengths = responseAI.getStrengths();
        this.weaknesses = responseAI.getWeaknesses();
        this.studentUserEntity = studentUserEntity;
        this.teacherUserEntity = teacherUserEntity;
        this.testingEntity = testing;
    }

    public Long getTestingResultId() {
        return testingResultId;
    }

    public void setTestingResultId(Long testingResultId) {
        this.testingResultId = testingResultId;
    }

    public LocalDateTime getAnalyzeAt() {
        return analyzeAt;
    }

    public void setAnalyzeAt(LocalDateTime analyzeAt) {
        this.analyzeAt = analyzeAt;
    }

    public Double getOverallMark() {
        return overallMark;
    }

    public void setOverallMark(Double overallMark) {
        this.overallMark = overallMark;
    }

    public List<String> getPoorlyStudiedTopics() {
        return poorlyStudiedTopics;
    }

    public void setPoorlyStudiedTopics(List<String> poorlyStudiedTopics) {
        this.poorlyStudiedTopics = poorlyStudiedTopics;
    }

    public List<String> getStudiedTopics() {
        return studiedTopics;
    }

    public void setStudiedTopics(List<String> studiedTopics) {
        this.studiedTopics = studiedTopics;
    }

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

    public List<AssignmentResultEntity> getAssignmentResultEntities() {
        return assignmentResultEntities;
    }

    public void setAssignmentResultEntities(List<AssignmentResultEntity> assignmentResultEntities) {
        this.assignmentResultEntities = assignmentResultEntities;
    }

    public TeacherUserEntity getTeacherUserEntity() {
        return teacherUserEntity;
    }

    public void setTeacherUserEntity(TeacherUserEntity teacherUserEntity) {
        this.teacherUserEntity = teacherUserEntity;
    }

    public StudentUserEntity getStudentUserEntity() {
        return studentUserEntity;
    }

    public void setStudentUserEntity(StudentUserEntity studentUserEntity) {
        this.studentUserEntity = studentUserEntity;
    }

    public ExtensiveTestingEntity getTestingEntity() {
        return testingEntity;
    }

    public void setTestingEntity(ExtensiveTestingEntity testingEntity) {
        this.testingEntity = testingEntity;
    }
}
