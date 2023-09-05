<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isErrorPage="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
</head>
<body>
	<div align="center">
		<h1>Error</h1>
		<br>
		<h2>
			<%=exception.getMessage()%></h2>

	</div>
</body>
</html>