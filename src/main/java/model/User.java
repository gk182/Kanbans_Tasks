package model;

public class User {
    private int id;                  // ID duy nhất của người dùng
    private String username;         // Tên đăng nhập
    private String email;            // Địa chỉ email
    private String passwordHash;     // Mật khẩu đã mã hóa
    private String createdAt;        // Ngày tạo tài khoản

    // Constructor
    public User(int id, String username, String email, String passwordHash, String createdAt) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.passwordHash = passwordHash;
        this.createdAt = createdAt;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}
