package com.example;
import jakarta.persistence.*;
import java.time.LocalDateTime;
@Entity
@Table(name = "favorites")
public class Favorite {

    @Id
    private Long id;

    @Column(name = "author_key", length = 255)
    private String authorKey;

    @Column(length = 2000)
    private String bio;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(length = 255)
    private String name;

    @Column(name = "top_work", length = 255)
    private String topWork;

    @Column(length = 255)
    private String username;

    @Column(name = "work_count")
    private Integer workCount;

    @PrePersist
    public void prePersist() {
        createdAt = LocalDateTime.now();
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthorKey() {
        return authorKey;
    }

    public void setAuthorKey(String authorKey) {
        this.authorKey = authorKey;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTopWork() {
        return topWork;
    }

    public void setTopWork(String topWork) {
        this.topWork = topWork;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getWorkCount() {
        return workCount;
    }

    public void setWorkCount(Integer workCount) {
        this.workCount = workCount;
    }
}