<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Task" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Kanban To-Do App</title>
    <link rel="stylesheet" href="css/kanban.css">
    <link rel="stylesheet" href="css/button.css"> <!-- Đường dẫn tới file CSS -->
    <script src="js/app.js"></script> <!-- JavaScript để xử lý kéo thả -->
</head>
<body>
    <div class="header">
        <h1>Kanban To-Do App</h1>
        <!-- Nút logout -->
        <a href="UserController?action=logout" class="btn-logout">Logout</a> <!-- Gọi hàm logoutUser -->
    </div>
	<div class="kanban-container">
   		<div class="kanban-board">
        <!-- To-Do Column -->
        	<div class="column" id="todo">
            	<h2>To Do</h2>
            	<div class="task-container" ondrop="drop(event)" ondragover="allowDrop(event)">
	                <!-- Vòng lặp hiển thị các task thuộc To-Do -->
	                <%
	                    List<Task> todoTasks = (List<Task>) request.getAttribute("todoTasks");
	                    if (todoTasks != null) {
	                        for (Task task : todoTasks) {
	                %>
	                    <div class="task" id="task<%= task.getId() %>" draggable="true" ondragstart="drag(event)">
	                        <%= task.getTitle() %>
	                    </div>
	                <%
	                        }
	                    } else {
	                %>
	                    <div>Không có task nào trong danh sách To-Do.</div>
	                <%
	                    }
	                %>
            </div>
        </div>

        <!-- In Progress Column -->
        <div class="column" id="in-progress">
            <h2>In Progress</h2>
            <div class="task-container" ondrop="drop(event)" ondragover="allowDrop(event)">
                <!-- Vòng lặp hiển thị các task thuộc In Progress -->
                <%
                    List<Task> inProgressTasks = (List<Task>) request.getAttribute("inProgressTasks");
                    if (inProgressTasks != null) {
                        for (Task task : inProgressTasks) {
                %>
                    <div class="task" id="task<%= task.getId() %>" draggable="true" ondragstart="drag(event)">
                        <%= task.getTitle() %>
                    </div>
                <%
                        }
                    } else {
                %>
                    <div>Không có task nào trong danh sách In Progress.</div>
                <%
                    }
                %>
            </div>
        </div>

        <!-- Done Column -->
        <div class="column" id="done">
            <h2>Done</h2>
            <div class="task-container" ondrop="drop(event)" ondragover="allowDrop(event)">
                <!-- Vòng lặp hiển thị các task thuộc Done -->
                <%
                    List<Task> doneTasks = (List<Task>) request.getAttribute("doneTasks");
                    if (doneTasks != null) {
                        for (Task task : doneTasks) {
                %>
                    <div class="task" id="task<%= task.getId() %>" draggable="true" ondragstart="drag(event)">
                        <%= task.getTitle() %>
                    </div>
                <%
                        }
                    } else {
                %>
                    <div>Không có task nào trong danh sách Done.</div>
                <%
                    }
                %>
            </div>
        </div>
    </div>
</div>
    <!-- Nút để thêm tác vụ mới -->
    <a href="task_form.jsp" class="btn">Thêm tác vụ mới</a>

    
</body>
</html>
