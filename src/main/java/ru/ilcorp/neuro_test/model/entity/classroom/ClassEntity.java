package ru.ilcorp.neuro_test.model.entity.classroom;

import jakarta.persistence.*;
import ru.ilcorp.neuro_test.model.dto.edclass.dtoClass;
import ru.ilcorp.neuro_test.model.entity.assignment.ExtensiveTestingEntity;
import ru.ilcorp.neuro_test.model.entity.user.StudentUserEntity;
import ru.ilcorp.neuro_test.model.entity.user.TeacherUserEntity;

import java.util.List;

@Entity
@Table(name = "class")
public class ClassEntity {
    @Id
    @Column(name = "id_class")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long classId;
    @Column(name = "class_name")
    private String className;
    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private TeacherUserEntity teacherUserEntity;
    @Embedded
    private ClassroomCode classroomCode;
    @OneToMany(mappedBy = "classEntity", cascade = CascadeType.ALL)
    private List<StudentUserEntity> studentUserEntities;

    @OneToMany(mappedBy = "classEntity")
    private List<ExtensiveTestingEntity> extensiveTestingEntities;

    @Column(name = "class_description")
    private String classDescription;

    public ClassEntity() {
    }

    public ClassEntity(dtoClass edClass, TeacherUserEntity teacherUserEntity) {
        this.className = edClass.getName();
        this.classroomCode = new ClassroomCode();
        this.teacherUserEntity = teacherUserEntity;
        this.classDescription = edClass.getDescription();
    }

    public Long getClassId() {
        return classId;
    }

    public void setClassId(Long classId) {
        this.classId = classId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public TeacherUserEntity getTeacherUserEntity() {
        return teacherUserEntity;
    }

    public void setTeacherUserEntity(TeacherUserEntity teacherUserEntity) {
        this.teacherUserEntity = teacherUserEntity;
    }

    public ClassroomCode getClassroomCode() {
        return classroomCode;
    }

    public void setClassroomCode(ClassroomCode classroomCode) {
        this.classroomCode = classroomCode;
    }

    public List<StudentUserEntity> getStudentUserEntities() {
        return studentUserEntities;
    }

    public void setStudentUserEntities(List<StudentUserEntity> studentUserEntities) {
        this.studentUserEntities = studentUserEntities;
    }

    public void updateClassroomCode() {
        this.classroomCode = new ClassroomCode();
    }

    public List<ExtensiveTestingEntity> getExtensiveTestingEntities() {
        return extensiveTestingEntities;
    }

    public void setExtensiveTestingEntities(List<ExtensiveTestingEntity> extensiveTestingEntities) {
        this.extensiveTestingEntities = extensiveTestingEntities;
    }

    public void setExtensiveTestingEntity(ExtensiveTestingEntity extensiveTestingEntity) {
        this.extensiveTestingEntities.add(extensiveTestingEntity);
    }

    public String getClassDescription() {
        return classDescription;
    }

    public void setClassDescription(String classDescription) {
        this.classDescription = classDescription;
    }
}
