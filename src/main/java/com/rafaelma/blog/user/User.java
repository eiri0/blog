package com.rafaelma.blog.user;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Objects;


@Entity
@Table(name = "tbl_user")
public class User {

    public User() { }

    public User(Long id, String userName, String hashedPassword, String salt) {
        this.id = id;
        this.userName = userName;
        this.hashedPassword = hashedPassword;
        this.salt = salt;
    }

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_name", nullable = false)
    @NotNull
    @NotBlank
    private String userName;

    @Column(name = "hashed_password", nullable = false)
    @NotNull
    @NotBlank
    private String hashedPassword;

    @Column(name = "salt", nullable = false)
    @NotNull
    @NotBlank
    private String salt;

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

    public String getHashedPassword() {
        return hashedPassword;
    }

    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(userName, user.userName) && Objects.equals(hashedPassword, user.hashedPassword) && Objects.equals(salt, user.salt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userName, hashedPassword, salt);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", hashedPassword='" + hashedPassword + '\'' +
                ", salt='" + salt + '\'' +
                '}';
    }
}
