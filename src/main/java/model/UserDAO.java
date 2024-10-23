package model;

import java.sql.*;
import util.*;

public class UserDAO {
    // Phương thức tạo người dùng mới
    public void createUser(User user) {
        String query = "INSERT INTO Users (username, email, password_hash) VALUES (?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getPasswordHash());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Phương thức kiểm tra thông tin đăng nhập
    public User login(String username, String passwordHash) {
        User user = null;
        String query = "SELECT * FROM Users WHERE username = ? AND password_hash = ?";
        try (
        	Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, username);
            stmt.setString(2, passwordHash);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                user = new User(
                    rs.getInt("id"),
                    rs.getString("username"),
                    rs.getString("email"),
                    rs.getString("password_hash"),
                    rs.getString("created_at")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
 // Phương thức kiểm tra xem tên người dùng đã tồn tại chưa
    public boolean checkUserExists(String username) {
        boolean userExists = false;
        String query = "SELECT * FROM Users WHERE username = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                userExists = true; // Nếu kết quả tồn tại, user đã tồn tại
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userExists;
    }
// DEBUG LOGIN    
    public static void main(String[] args) {
        UserDAO userDAO = new UserDAO();
        User user = userDAO.login("gk", "123"); // Thay đổi tên người dùng và mật khẩu để kiểm tra

        if (user != null) {
            System.out.println("Login successful! User: " + user.getUsername());
        } else {
            System.out.println("Login failed!");
        }
    }
    
}
