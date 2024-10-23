package controller;

import model.Task;
import model.TaskDAO;

import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Timestamp;

@WebServlet("/task")
public class TaskController extends HttpServlet {

    private TaskDAO taskDAO;

    @Override
    public void init() throws ServletException {
        taskDAO = new TaskDAO(); // Khởi tạo TaskDAO
    }

    // Tạo task mới
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);

        // Kiểm tra xem người dùng đã đăng nhập chưa (lấy userId từ session)
        if (session == null || session.getAttribute("userId") == null) {
            response.sendRedirect("login.jsp"); // Chuyển hướng đến trang đăng nhập nếu chưa đăng nhập
            return;
        }

        int userId = (int) session.getAttribute("userId"); // Lấy ID người dùng từ session
        String title = request.getParameter("title");
        String description = request.getParameter("description");

        // Kiểm tra xem title có bị bỏ trống không
        if (title == null || title.trim().isEmpty()) {
            request.setAttribute("errorMessage", "Tiêu đề của tác vụ không được để trống!");
            request.getRequestDispatcher("task_form.jsp").forward(request, response);
            return;
        }

        // Tạo đối tượng Task
        Task newTask = new Task(0, title.trim(), description != null ? description.trim() : "", "To-Do", userId, new Timestamp(System.currentTimeMillis()), null);
        taskDAO.createTask(newTask);

        // Chuyển hướng về trang chính sau khi tạo task
        response.sendRedirect("kanban.jsp");
    }

    // Cập nhật trạng thái task
    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int taskId = Integer.parseInt(request.getParameter("taskId"));
            String newStatus = request.getParameter("newStatus");

            if (newStatus == null || newStatus.trim().isEmpty()) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST); // Trạng thái yêu cầu không hợp lệ
                response.getWriter().write("Trạng thái không hợp lệ!");
                return;
            }

            // Cập nhật trạng thái task
            taskDAO.updateTaskStatus(taskId, newStatus.trim());
            response.setStatus(HttpServletResponse.SC_OK); // Trả về trạng thái OK
        } catch (NumberFormatException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("ID của task không hợp lệ!");
        }
    }

    // Xóa task
    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int taskId = Integer.parseInt(request.getParameter("taskId"));

            // Kiểm tra nếu taskId là hợp lệ
            taskDAO.deleteTask(taskId);
            response.setStatus(HttpServletResponse.SC_OK); // Trả về trạng thái OK
        } catch (NumberFormatException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("ID của task không hợp lệ!");
        }
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        int userId = (int) session.getAttribute("userId"); // Lấy userId từ session

        // Lấy danh sách task của người dùng từ cơ sở dữ liệu
        List<Task> todoTasks = taskDAO.getTasksByStatus(userId, "To-Do");
        List<Task> inProgressTasks = taskDAO.getTasksByStatus(userId, "In Progress");
        List<Task> doneTasks = taskDAO.getTasksByStatus(userId, "Done");

        // Đặt task vào request để truyền sang kanban.jsp
        request.setAttribute("todoTasks", todoTasks);
        request.setAttribute("inProgressTasks", inProgressTasks);
        request.setAttribute("doneTasks", doneTasks);

        // Chuyển hướng đến trang kanban.jsp
        request.getRequestDispatcher("kanban.jsp").forward(request, response);
    }

}
