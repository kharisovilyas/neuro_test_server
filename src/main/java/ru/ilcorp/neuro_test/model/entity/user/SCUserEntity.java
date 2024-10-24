package ru.ilcorp.neuro_test.model.entity.user;

import jakarta.persistence.*;
import ru.ilcorp.neuro_test.model.entity.organization.OrganizationEntity;

@MappedSuperclass
public class SCUserEntity {
    @Id
    @Column(name = "id_user")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long userId;

    @Column(name = "is_teacher")
    protected Boolean isTeacher;

    @Column(name = "name_of_user")
    protected String nameOfUser;

    @Column(name = "surname_of_user")
    protected String surnameOfUser;

    @Column(name = "email")
    protected String email;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "organization")
    protected OrganizationEntity organizationEntity;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_user_auth")
    protected UserAuthEntity userAuthEntity;

    public SCUserEntity() {}

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Boolean getTeacher() {
        return isTeacher;
    }

    public void setTeacher(Boolean teacher) {
        isTeacher = teacher;
    }

    public String getNameOfUser() {
        return nameOfUser;
    }

    public void setNameOfUser(String nameOfUser) {
        this.nameOfUser = nameOfUser;
    }

    public String getSurnameOfUser() {
        return surnameOfUser;
    }

    public void setSurnameOfUser(String surnameOfUser) {
        this.surnameOfUser = surnameOfUser;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public OrganizationEntity getOrganizationEntity() {
        return organizationEntity;
    }

    public void setOrganizationEntity(OrganizationEntity organizationEntity) {
        this.organizationEntity = organizationEntity;
    }

    public UserAuthEntity getUserAuthEntity() {
        return userAuthEntity;
    }

    public void setUserAuthEntity(UserAuthEntity userAuthEntity) {
        this.userAuthEntity = userAuthEntity;
    }
}
