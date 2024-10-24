package ru.ilcorp.neuro_test.model.entity.organization;

import jakarta.persistence.*;
import ru.ilcorp.neuro_test.model.entity.user.SCUserEntity;
import ru.ilcorp.neuro_test.model.entity.user.StudentUserEntity;
import ru.ilcorp.neuro_test.model.entity.user.TeacherUserEntity;

@Entity
@Table(name = "organization")
public class OrganizationEntity {
    @Id
    @Column(name = "id_organization")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long organizationId;

    @Column(name = "name_of_organization")
    private String organizationName;

    @OneToOne(mappedBy = "organizationEntity")
    @JoinColumn(name = "id_student_user")
    private StudentUserEntity studentUserEntity;

    @OneToOne(mappedBy = "organizationEntity")
    @JoinColumn(name = "id_student_user")
    private TeacherUserEntity teacherUserEntity;

    public OrganizationEntity() {
    }


    public Long getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Long organizationId) {
        this.organizationId = organizationId;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public StudentUserEntity getStudentUserEntity() {
        return studentUserEntity;
    }

    public void setStudentUserEntity(StudentUserEntity studentUserEntity) {
        this.studentUserEntity = studentUserEntity;
    }

    public TeacherUserEntity getTeacherUserEntity() {
        return teacherUserEntity;
    }

    public void setTeacherUserEntity(TeacherUserEntity teacherUserEntity) {
        this.teacherUserEntity = teacherUserEntity;
    }
}
