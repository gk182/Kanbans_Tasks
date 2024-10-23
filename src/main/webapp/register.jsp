<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login and Register Form</title>
    <!-- Font Awesome CDN -->
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="css/button.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    
</head>
<body>

	<div class="container" id="login-form" style="display:none;">
	    <h2>Login</h2>
	    <!-- Hiển thị thông báo lỗi nếu có -->
	    <c:if test="${not empty errorMessage}">
	        <p style="color:red;text-align: center;">${errorMessage}</p>
	    </c:if>
	    <form action="user" method="POST">
	        <!-- Hidden field to specify the login action -->
	        <input type="hidden" name="action" value="login">
	        
	        <div class="input-container">
	            <input type="text" name="username" placeholder="Username" required>
	        </div>
	        <div class="input-container">
	            <input type="password" name="password" placeholder="Password" id="login-password" required>
	            <i class="fas fa-eye eye-icon" id="eye-icon-login" onclick="togglePassword('login-password', 'eye-icon-login')"></i>
	        </div>
	        <button type="submit">Login</button>
	    </form>
	    <div class="toggle">
	        <p>Don't have an account? <a href="#" onclick="toggleForms()">Register here</a></p>
	    </div>
	</div>

	<div class="container" id="register-form" >
   		<h2>Register</h2>
   		<!-- Hiển thị thông báo lỗi nếu có -->
	    <c:if test="${not empty errorMessage}">
	        <p style="color:red;text-align: center;">${errorMessage}</p>
	    </c:if>
	    <form action="user" method="POST" onsubmit="return validatePassword()">
	        <!-- Hidden field to specify the register action -->
	        <input type="hidden" name="action" value="register">
	
	        <div class="input-container">
	            <input type="text" name="username" placeholder="Username" required>
	        </div>
	        <div class="input-container">
	            <input type="email" name="email" placeholder="Email" required>
	        </div>
	        <div class="input-container">
	            <input type="password" name="password" placeholder="Password" id="register-password" required>
	            <i class="fas fa-eye eye-icon" id="eye-icon-register" onclick="togglePassword('register-password', 'eye-icon-register')"></i>
	        </div>
	        <div class="input-container">
	            <input type="password" name="confirmPassword" placeholder="Confirm Password" id="confirm-password" required>
	            <i class="fas fa-eye eye-icon" id="eye-icon-confirm" onclick="togglePassword('confirm-password', 'eye-icon-confirm')"></i>
	        </div>
	        <span id="error-message" class="error"></span>
	        <button type="submit">Register</button>
	    </form>
	    <div class="toggle">
	        <p>Already have an account? <a href="#" onclick="toggleForms()">Login here</a></p>
	    </div>
	</div>
	
	<script>
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
	</script>

</body>
</html>

