<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>

<html>
<head>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>



<style>
div {
	text-align: center;
}

td, th {
	text-align: center;
	border: 1px solid black;
	padding: 8px;
}

table {
	align: center;
	text-align: center;
	border: 1px solid black;
	border-collapse: collapse;
}
</style>
</head>
<body>


	<div align="center">
		<br> <br>
		<form action="listAll" method="post">
			<input type="submit" value="Show Birthday List">
		</form>
		<br> <br>
		<form action="editSaved" method="post">
			<input type="hidden" name="id" value="${editId}"> Age: <input
				type="number" name="age" value="${editAge}" min="0" max="255"
				size="5"> Name: <input type="text" name="name" size="15"
				value="${editName}"> Birthday Month: <input type="number"
				name="month" value="${editMonth}" min="1" max="12" size="4">
			Birthday Day: <input type="number" name="day" value="${editDay}"
				min="1" max="31" size="4"> <input type="submit" value="Save">
		</form>
		<br> ${errorMessage} <br> <br>
	</div>


</body>
</html>
