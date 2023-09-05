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
<title>Insert title here</title>
</head>
<body>
	<div align="center">
		<br>
		<form class="form-container" action="registerSaved" method="post">
			<br> Username:&nbsp; <input type="text" minlength="3"
				maxlength="40" name="username" size="15" required>
			&nbsp;&nbsp; Password: &nbsp;<input type="password" minlength="3"
				maxlength="40" name="password" size="15" required>
			&nbsp;&nbsp;<input class="form-container" type="submit"
				name="register" value="Sign Up"> <br> <br> <br>
		</form>
		<br> ${error} <br>

	</div>
</body>
</html>