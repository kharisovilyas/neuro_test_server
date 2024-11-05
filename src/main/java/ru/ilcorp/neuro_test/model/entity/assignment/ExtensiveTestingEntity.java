package ru.ilcorp.neuro_test.model.entity.assignment;

import jakarta.persistence.*;
import ru.ilcorp.neuro_test.model.dto.assignment.dtoTesting;
import ru.ilcorp.neuro_test.model.entity.classroom.ClassEntity;
import ru.ilcorp.neuro_test.model.entity.user.TeacherUserEntity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "testing")
public class ExtensiveTestingEntity {
    @Id
    @Column(name = "id_testing")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long testingId;
    @Column(name = "title")
    private String title;
    @Column(name = "due_data")
    private LocalDateTime dueDate;
    @Column(name = "create_at")
    private LocalDateTime createdAt;
    @Column(name = "late_submission")
    private Boolean lateSubmission;
    @OneToMany(mappedBy = "extensiveTestingEntity")
    private List<AssignmentEntity> assignmentEntities;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "teacher_id")
    private TeacherUserEntity teacherUserEntity;
    @ManyToOne
    @JoinColumn(name = "class_id")
    private ClassEntity classEntity;

    public ExtensiveTestingEntity() {
    }

    public ExtensiveTestingEntity(dtoTesting testing, TeacherUserEntity teacherUserEntity, ClassEntity edClass, List<AssignmentEntity> assignments) {
        teacherUserEntity.setTestingEntity(this);
        edClass.setExtensiveTestingEntity(this);
        assignments = assignments.stream().peek(assignmentEntity -> assignmentEntity.setExtensiveTestingEntity(this)).collect(Collectors.toList());
        this.title = testing.getTitle();
        this.title = testing.getTitle();
        this.dueDate = testing.getDueDate();
        this.createdAt = LocalDateTime.now();
        this.lateSubmission = testing.getLateSubmission();
        this.teacherUserEntity = teacherUserEntity;
        this.classEntity = edClass;
        this.assignmentEntities = assignments;
    }

    public Long getTestingId() {
        return testingId;
    }

    public void setTestingId(Long testingId) {
        this.testingId = testingId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<AssignmentEntity> getAssignmentEntities() {
        return assignmentEntities;
    }

    public void setAssignmentEntities(List<AssignmentEntity> assignmentEntities) {
        this.assignmentEntities = assignmentEntities;
    }

    public TeacherUserEntity getTeacherUserEntity() {
        return teacherUserEntity;
    }

    public void setTeacherUserEntity(TeacherUserEntity teacherUserEntity) {
        this.teacherUserEntity = teacherUserEntity;
    }

    public ClassEntity getClassEntity() {
        return classEntity;
    }

    public void setClassEntity(ClassEntity classEntity) {
        this.classEntity = classEntity;
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDateTime dueDate) {
        this.dueDate = dueDate;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Boolean getLateSubmission() {
        return lateSubmission;
    }

    public void setLateSubmission(Boolean lateSubmission) {
        this.lateSubmission = lateSubmission;
    }
}
