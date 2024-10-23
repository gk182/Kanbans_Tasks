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
		<script src="js/app.js"></script> <!-- JavaScript để xử lý kéo thả -->
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">

	</head>

	<body>

		<div class="container" id="login-form">
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
					<i class="fas fa-eye eye-icon" id="eye-icon-login"
						onclick="togglePassword('login-password', 'eye-icon-login')"></i>
				</div>
				<button type="submit">Login</button>
			</form>
			<div class="toggle">
				<p>Don't have an account? <a href="#" onclick="toggleForms()">Register here</a></p>
			</div>
		</div>

		<div class="container" id="register-form" style="display:none;">
			<!-- Hiển thị thông báo lỗi nếu có -->
			<c:if test="${not empty errorMessage}">
				<p style="color:red;text-align: center;">${errorMessage}</p>
			</c:if>
			<h2>Register</h2>
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
					<i class="fas fa-eye eye-icon" id="eye-icon-register"
						onclick="togglePassword('register-password', 'eye-icon-register')"></i>
				</div>
				<div class="input-container">
					<input type="password" name="confirmPassword" placeholder="Confirm Password" id="confirm-password"
						required>
					<i class="fas fa-eye eye-icon" id="eye-icon-confirm"
						onclick="togglePassword('confirm-password', 'eye-icon-confirm')"></i>
				</div>
				<span id="error-message" class="error"></span>
				<button type="submit">Register</button>
			</form>
			<div class="toggle">
				<p>Already have an account? <a href="#" onclick="toggleForms()">Login here</a></p>
			</div>
		</div>


	</body>

	</html>