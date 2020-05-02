package com.tahminapp.auth.domain;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name ="user")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    private String userName;

    @NotEmpty
    private String email;

    @NotEmpty
    private String password;

    @NotEmpty
    private String name;

    @NotEmpty
    private String surname;

    private UserStatus status;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_authority",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "authority_id"))
    private List<Authority> authorities;

    public  User() {}

    public User(String userName , String email, String password , String name , String surname, UserStatus status, List<Authority> authorities) {
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.status = status;
        this.authorities = authorities;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public List<Authority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<Authority> authorities) {
        this.authorities = authorities;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }
}
