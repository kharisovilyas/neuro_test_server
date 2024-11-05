package ru.ilcorp.neuro_test.model.entity.user;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "user_auth")
public class UserAuthEntity {
    @Id
    @Column(name = "id_user_auth")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userAuthId;
    //TODO: вставлять email - он уже уникален так как
    @Column(name = "unique_username", unique = true)
    private String uniqueUsername;
    @Column(name = "password")
    private String password;
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "role")
    private Set<String> roles; // Роли пользователя
    @Column(name = "confirm_email")
    private Boolean confirmEmail;
    @Embedded
    private UserAccessCode userAccessCode;


    public UserAuthEntity(String email, String encode, Set<String> roles) {
        this.uniqueUsername = email;
        this.password = encode;
        this.roles = roles;
        this.confirmEmail = false;
    }

    public UserAuthEntity(){}

    public Long getUserAuthId() {
        return userAuthId;
    }

    public void setUserAuthId(Long userAuthId) {
        this.userAuthId = userAuthId;
    }

    public String getUniqueUsername() {
        return uniqueUsername;
    }

    public void setUniqueUsername(String uniqueUsername) {
        this.uniqueUsername = uniqueUsername;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }

    public UserAccessCode getUserAccessCode() {
        return userAccessCode;
    }

    public void setUserAccessCode(UserAccessCode userAccessCode) {
        this.userAccessCode = userAccessCode;
    }

    public Boolean getConfirmEmail() {
        return confirmEmail;
    }

    public void setConfirmEmail(Boolean confirmEmail) {
        this.confirmEmail = confirmEmail;
    }
}
