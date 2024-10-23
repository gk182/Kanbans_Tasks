package model;

import java.sql.Timestamp;

public class Task {
    private int id;                  // ID duy nhất của task
    private String title;            // Tiêu đề của task
    private String description;      // Mô tả chi tiết của task
    private String status;           // Trạng thái của task (To-Do, In-Progress, Done)
    private int userId;             // ID người dùng tạo task
    private Timestamp createdAt;     // Thời gian tạo task
    private Timestamp updatedAt;     // Thời gian cập nhật task

    // Constructor
    public Task(int id, String title, String description, String status, int userId, Timestamp createdAt, Timestamp updatedAt) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = status;
        this.userId = userId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }
}
