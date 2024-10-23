// Toggle show/hide password with eye icon
function togglePassword(inputId, iconId) {
    var passwordInput = document.getElementById(inputId);
    var icon = document.getElementById(iconId);

    if (passwordInput.type === "password") {
        passwordInput.type = "text";
        icon.classList.remove('fa-eye');
        icon.classList.add('fa-eye-slash'); // Chuyển thành biểu tượng "đóng mắt"
    } else {
        passwordInput.type = "password";
        icon.classList.remove('fa-eye-slash');
        icon.classList.add('fa-eye'); // Chuyển lại biểu tượng "mở mắt"
    }
}

// Toggle between login and register forms
function toggleForms() {
    var loginForm = document.getElementById('login-form');
    var registerForm = document.getElementById('register-form');
    if (loginForm.style.display === "none") {
        loginForm.style.display = "block";
        registerForm.style.display = "none";
    } else {
        loginForm.style.display = "none";
        registerForm.style.display = "block";
    }
}

// Validate if the passwords match
function validatePassword() {
    var password = document.getElementById("register-password").value;
    var confirmPassword = document.getElementById("confirm-password").value;
    var errorMessage = document.getElementById("error-message");

    if (password !== confirmPassword) {
        errorMessage.textContent = "Passwords do not match!";
        return false; // Prevent form submission
    } else {
        errorMessage.textContent = ""; // Clear any previous error
        return true; // Allow form submission
    }
}
// Cho phép kéo và thả tác vụ
function allowDrop(event) {
    event.preventDefault(); // Ngăn chặn hành động mặc định để cho phép thả
}

// Xử lý sự kiện kéo bắt đầu
function drag(event) {
    event.dataTransfer.setData("text", event.target.id); // Lưu id của task được kéo
}

// Xử lý khi thả tác vụ vào cột mới
function drop(event) {
    event.preventDefault(); // Ngăn chặn hành động mặc định để cho phép thả

    // Lấy id của task từ dữ liệu đã lưu khi kéo
    var taskId = event.dataTransfer.getData("text");
    
    // Lấy id của cột mới mà task được thả vào
    var newColumn = event.target.id;

    // Di chuyển task vào cột mới
    event.target.appendChild(document.getElementById(taskId));

    // Cập nhật trạng thái của task bằng AJAX
    updateTaskStatus(taskId.replace("task", ""), newColumn);
}

// Hàm cập nhật trạng thái task qua AJAX
function updateTaskStatus(taskId, newStatus) {
    // Tạo đối tượng XMLHttpRequest để gửi yêu cầu AJAX
    var xhr = new XMLHttpRequest();
    xhr.open("POST", "task", true); // Gửi yêu cầu POST đến server
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded"); // Đặt header

    // Xử lý khi nhận được phản hồi từ server
    xhr.onreadystatechange = function () {
        if (xhr.readyState === XMLHttpRequest.DONE) {
            if (xhr.status === 200) {
                console.log("Cập nhật trạng thái thành công cho task ID: " + taskId);
            } else {
                console.error("Có lỗi xảy ra khi cập nhật trạng thái cho task ID: " + taskId);
            }
        }
    };

    // Gửi dữ liệu taskId và newStatus đến server
    xhr.send("taskId=" + taskId + "&newStatus=" + newStatus);
}