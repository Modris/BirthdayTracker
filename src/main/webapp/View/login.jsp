<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<style>
.form-container {
	text-align: center;
	justify-content: center; /* Center horizontally */
	align-items: center; /* Center vertically */
	display: flex
}
</style>
<meta charset="UTF-8">
</head>
<body>
	<div align="center">
		<br>
		<div class="form-container" align="center">
			<form action="register" method="post">
				New here? <input type="submit" name="register" value="Register">
			</form>
			&nbsp;&nbsp;Or&nbsp;&nbsp;
			<form action="registerGuest" method="post">
				<input type="submit" name="guest" value="Proceed as a Guest">
			</form>
		</div>
		<br> <br>
		</form>
		<form class="form-container" action="login" method="post">
			<br> Username:&nbsp; <input type="text" minlength="3"
				maxlength="40" name="username" size="15" required>
			&nbsp;&nbsp; Password: &nbsp;<input type="password" minlength="3"
				maxlength="40" name="password" size="15" required>
			&nbsp;&nbsp;<input class="form-container" type="submit" name="login"
				value="Login">
		</form>
		<br> ${error}
	</div>
</body>
</html>