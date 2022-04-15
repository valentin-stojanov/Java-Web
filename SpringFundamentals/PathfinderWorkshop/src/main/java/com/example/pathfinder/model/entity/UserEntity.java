package com.example.pathfinder.model.entity;

import com.example.pathfinder.model.entity.enums.UserlevelEnum;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class UserEntity extends BaseEntity {

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(name = "fullname", nullable = false)
    private String fullName;

    private int age;

    @Column(nullable = false, unique = true)
    private String email;

    private UserlevelEnum level;

    @ManyToMany
    private RoleEntity role;

    public UserEntity() {
    }

    public RoleEntity getRole() {
        return role;
    }

    public void setRole(RoleEntity role) {
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserlevelEnum getLevel() {
        return level;
    }

    public void setLevel(UserlevelEnum level) {
        this.level = level;
    }
}
