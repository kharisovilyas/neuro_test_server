package ru.ilcorp.neuro_test.model.entity.user;

import jakarta.persistence.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import ru.ilcorp.neuro_test.model.dto.user.dtoStudentUserInformation;
import ru.ilcorp.neuro_test.model.entity.assignment.result.ClassProgressEntity;
import ru.ilcorp.neuro_test.model.entity.assignment.statistic.StudentStatisticsEntity;
import ru.ilcorp.neuro_test.model.entity.classroom.ClassEntity;
import ru.ilcorp.neuro_test.model.entity.classroom.ClassroomCode;
import ru.ilcorp.neuro_test.model.entity.classroom.ClassroomEntity;

import java.util.List;

@Entity
@Table(name = "user_student")
public class StudentUserEntity extends SCUserEntity {

    @Embedded
    private ClassroomCode classRoomCode;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_class_room")
    private ClassroomEntity classroomEntity;

    @OneToMany(mappedBy = "studentUserEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    @Fetch(FetchMode.JOIN)
    private List<StudentStatisticsEntity> studentStatisticsEntities;

    @ManyToOne(cascade = CascadeType.ALL)
    private ClassEntity classEntity;

    @OneToMany(mappedBy = "studentUserEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    @Fetch(FetchMode.JOIN)
    private List<ClassProgressEntity> classProgressEntities;

    public StudentUserEntity(dtoStudentUserInformation student, UserAuthEntity userAuthEntity, ClassEntity classEntity) {
        this.nameOfUser = student.getNameOfUser();
        this.surnameOfUser = student.getSurnameOfUser();
        this.isTeacher = student.getIsTeacher();
        this.email = student.getEmail();
        this.userAuthEntity = userAuthEntity;
        this.classEntity = classEntity;
    }

    public StudentUserEntity() {
    }

    public ClassroomCode getClassRoomCode() {
        return classRoomCode;
    }

    public void setClassRoomCode(ClassroomCode classRoomCode) {
        this.classRoomCode = classRoomCode;
    }

    public ClassroomEntity getClassroomEntity() {
        return classroomEntity;
    }

    public void setClassroomEntity(ClassroomEntity classroomEntity) {
        this.classroomEntity = classroomEntity;
    }

    public List<StudentStatisticsEntity> getStudentStatisticsEntities() {
        return studentStatisticsEntities;
    }

    public void setStudentStatisticsEntities(List<StudentStatisticsEntity> studentStatisticsEntities) {
        this.studentStatisticsEntities = studentStatisticsEntities;
    }

    public ClassEntity getClassEntity() {
        return classEntity;
    }

    public void setClassEntity(ClassEntity classEntity) {
        this.classEntity = classEntity;
    }

    public List<ClassProgressEntity> getClassProgressEntities() {
        return classProgressEntities;
    }

    public void setClassProgressEntities(List<ClassProgressEntity> classProgressEntities) {
        this.classProgressEntities = classProgressEntities;
    }
}
