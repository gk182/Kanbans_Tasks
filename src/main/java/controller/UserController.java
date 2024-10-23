package controller;

import model.User;
import model.UserDAO;

import java.text.SimpleDateFormat;
import java.util.Date;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/user")
public class UserController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private UserDAO userDAO;

    @Override
    public void init() throws ServletException {
        userDAO = new UserDAO(); // Khởi tạo UserDAO
    }

    // Đăng ký người dùng mới
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        System.out.println("Action received: " + action); // Ghi nhận hành động nhận được

        if ("register".equals(action)) {
            registerUser(request, response);
        } else if ("login".equals(action)) {
            loginUser(request, response);
        } else {
            response.sendRedirect("login.jsp"); // Nếu không có hành động hợp lệ, chuyển hướng đến trang đăng nhập
        }
        
        if ("logout".equals(action)) {
            logoutUser(request, response); // Gọi hàm logout khi người dùng yêu cầu
        }
        
    }

    // Phương thức đăng ký người dùng mới
    private void registerUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        // Mã hóa mật khẩu trước khi lưu vào cơ sở dữ liệu (sử dụng một thư viện mã hóa thích hợp)
        String passwordHash = password; // Nên sử dụng hàm mã hóa thực tế

        // Tạo đối tượng User và thêm vào cơ sở dữ liệu
        String createdAt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()); // Lấy thời gian hiện tại dưới dạng String
        User newUser = new User(0, username, email, passwordHash, createdAt);

        // Kiểm tra xem người dùng đã tồn tại chưa
        if (userDAO.checkUserExists(username)) {
            request.setAttribute("errorMessage", "Username already exists. Please choose another one.");
            request.getRequestDispatcher("register.jsp").forward(request, response);
        } else {
            // Tạo người dùng mới
            userDAO.createUser(newUser);
            response.sendRedirect("index.jsp"); // Chuyển hướng đến trang đăng nhập sau khi đăng ký thành công
        }
    }

    // Phương thức đăng nhập người dùng
    private void loginUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Mã hóa mật khẩu trước khi kiểm tra (sử dụng cùng một hàm mã hóa)
        String passwordHash = password; // Nên sử dụng hàm mã hóa thực tế

        // Kiểm tra thông tin đăng nhập
        User user = userDAO.login(username, passwordHash);
        if (user != null) {
            HttpSession session = request.getSession();
            session.setAttribute("user", user); // Lưu thông tin người dùng vào session
            session.setAttribute("userId", user.getId()); // Lưu ID người dùng vào session

            // Lấy các task của người dùng từ cơ sở dữ liệu
//            TaskDAO taskDAO = new TaskDAO();
//            int userId = user.getId(); // Lấy userId từ đối tượng user
//            List<Task> todoTasks = taskDAO.getTasksByStatus(userId, "To-Do");
//            List<Task> inProgressTasks = taskDAO.getTasksByStatus(userId, "In Progress");
//            List<Task> doneTasks = taskDAO.getTasksByStatus(userId, "Done");
//
//            // Đặt các danh sách task vào request để truyền sang JSP
//            request.setAttribute("todoTasks", todoTasks);
//            request.setAttribute("inProgressTasks", inProgressTasks);
//            request.setAttribute("doneTasks", doneTasks);
//            request.getRequestDispatcher("kanban.jsp").forward(request, response);
            // Chuyển hướng đến trang Kanban sau khi đăng nhập thành công
//            response.sendRedirect("kanban.jsp");
            
            response.sendRedirect("kanban.jsp");  // Thay vì chuyển thẳng tới kanban.jsp

        } else {
            // Nếu đăng nhập thất bại, hiển thị thông báo lỗi
            request.setAttribute("errorMessage", "Incorrect username or password!");
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
        
    }
    
    protected void logoutUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false); // Lấy session hiện tại
        if (session != null) {
            session.invalidate(); // Hủy session
        }
        response.sendRedirect("index.jsp"); // Chuyển hướng về trang đăng nhập
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("index.jsp"); // Chuyển hướng đến trang đăng nhập nếu truy cập GET
    }
}
