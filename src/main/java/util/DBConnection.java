package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/kanban_tasks"; // Đường dẫn đến cơ sở dữ liệu
    private static final String USER = "root"; // Tên người dùng MySQL
    private static final String PASSWORD = "2782004"; // Mật khẩu MySQL

    // Phương thức để lấy kết nối đến cơ sở dữ liệu
    public static Connection getConnection() {
        Connection connection = null; // Khai báo biến kết nối
        try {
            // Tải driver MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Trả về kết nối đến cơ sở dữ liệu
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Kết nối thành công đến cơ sở dữ liệu!");
        } catch (ClassNotFoundException e) {
            System.err.println("Lỗi: Driver không tìm thấy!");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Lỗi: Không thể kết nối đến cơ sở dữ liệu!");
            e.printStackTrace();
        }
        return connection; // Trả về kết nối, có thể là null nếu gặp lỗi
    }

    
    
    // Phương thức kiểm tra kết nối đến cơ sở dữ liệu
//    public static void main(String[] args) {
//        Connection conn = getConnection();
//        if (conn != null) {
//            try {
//                System.out.println("Kết nối đã được thiết lập.");
//                conn.close(); // Đóng kết nối sau khi kiểm tra
//            } catch (SQLException e) {
//                System.err.println("Lỗi khi đóng kết nối!");
//                e.printStackTrace();
//            }
//        } else {
//            System.err.println("Không thể thiết lập kết nối!");
//        }
//    }
}
