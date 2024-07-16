package com.training.backend.dto;

public class UserProgressDTO {
    private Long userId;
    private String username;
    private float progress;

    public UserProgressDTO() {
    }

    public UserProgressDTO(Long userId, String username, float progress) {
        this.userId = userId;
        this.username = username;
        this.progress = progress;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public float getProgress() {
        return progress;
    }

    public void setProgress(float progress) {
        this.progress = progress;
    }
}
