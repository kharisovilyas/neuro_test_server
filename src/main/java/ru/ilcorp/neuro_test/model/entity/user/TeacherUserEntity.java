package ru.ilcorp.neuro_test.model.entity.user;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import ru.ilcorp.neuro_test.model.dto.user.dtoTeacherUserInformation;
import ru.ilcorp.neuro_test.model.entity.assignment.ExtensiveTestingEntity;
import ru.ilcorp.neuro_test.model.entity.assignment.criteria.AssignmentDetailCriteriaEntity;
import ru.ilcorp.neuro_test.model.entity.assignment.result.ClassProgressEntity;
import ru.ilcorp.neuro_test.model.entity.assignment.result.ExtensiveTestingResultEntity;
import ru.ilcorp.neuro_test.model.entity.classroom.ClassEntity;

import java.util.List;

@Entity
@Table(name = "user_teacher")
public class TeacherUserEntity extends SCUserEntity {

    @OneToMany(mappedBy = "teacherUserEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    @Fetch(FetchMode.JOIN)
    private List<ExtensiveTestingEntity> testingEntities;

    @OneToMany(mappedBy = "teacherUserEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    @Fetch(FetchMode.JOIN)
    private List<ExtensiveTestingResultEntity> testingResultEntities;

    @OneToMany(mappedBy = "teacherUserEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    @Fetch(FetchMode.JOIN)
    private List<AssignmentDetailCriteriaEntity> assignmentDetailedCriteriaEntities;

    @OneToMany(mappedBy = "teacherUserEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    @Fetch(FetchMode.JOIN)
    private List<ClassEntity> classEntities;

    @OneToMany(mappedBy = "teacherUserEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    @Fetch(FetchMode.JOIN)
    private List<ClassProgressEntity> classProgressEntities;

    public TeacherUserEntity(dtoTeacherUserInformation teacher, UserAuthEntity userAuth) {
        this.nameOfUser = teacher.getNameOfUser();
        this.surnameOfUser = teacher.getSurnameOfUser();
        this.email = teacher.getEmail();
        this.isTeacher = teacher.getIsTeacher();
        this.userAuthEntity = userAuth;
    }

    public TeacherUserEntity() {
    }

    public List<ExtensiveTestingEntity> getTestingEntities() {
        return testingEntities;
    }

    public void setTestingEntities(List<ExtensiveTestingEntity> testingEntities) {
        this.testingEntities = testingEntities;
    }

    public List<AssignmentDetailCriteriaEntity> getAssignmentDetailedCriteriaEntities() {
        return assignmentDetailedCriteriaEntities;
    }

    public void setAssignmentDetailedCriteriaEntities(List<AssignmentDetailCriteriaEntity> assignmentDetailedCriteriaEntities) {
        this.assignmentDetailedCriteriaEntities = assignmentDetailedCriteriaEntities;
    }

    public List<ClassEntity> getClassEntities() {
        return classEntities;
    }

    public void setClassEntities(List<ClassEntity> classEntities) {
        this.classEntities = classEntities;
    }

    public List<ClassProgressEntity> getClassProgressEntities() {
        return classProgressEntities;
    }

    public void setClassProgressEntities(List<ClassProgressEntity> classProgressEntities) {
        this.classProgressEntities = classProgressEntities;
    }

    public void setTestingEntity(ExtensiveTestingEntity testingEntity) {
        this.testingEntities.add(testingEntity);
    }

    public List<ExtensiveTestingResultEntity> getTestingResultEntities() {
        return testingResultEntities;
    }

    public void setTestingResultEntities(List<ExtensiveTestingResultEntity> testingResultEntities) {
        this.testingResultEntities = testingResultEntities;
    }
}
