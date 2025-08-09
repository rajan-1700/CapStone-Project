package com.example.UserAuthentication.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
public class UserDTO {
    @Id
    private Integer uid;
    private String email;
    private String password;

    public UserDTO(Integer uid, String email, String password) {
        this.uid = uid;
        this.email = email;
        this.password = password;
    }

    public UserDTO() {

    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
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

    @Override
    public String toString() {
        return "UserDTO{" +
                "uid=" + uid +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
