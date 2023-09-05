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
	table-layout: fixed;
	white-space: nowrap;
}

.up-arrow {
	display: inline-block;
	width: 0;
	height: 0;
	border-left: 10px solid transparent;
	border-right: 10px solid transparent;
	border-bottom: 10px solid green;
}

.down-arrow {
	display: inline-block;
	width: 0;
	height: 0;
	border-left: 10px solid transparent;
	border-right: 10px solid transparent;
	border-top: 10px solid red;
}

.form-container {
	text-align: center;
	justify-content: center; /* Center horizontally */
	align-items: center; /* Center vertically */
	display: flex
}
</style>

</head>
<body>
	<%
	response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
	response.setHeader("Pragma", "no-cache"); // HTTP 1.0
	response.setHeader("Expires", "0"); // Proxies.
	if (session.getAttribute("username") == null) {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/View/login.jsp");
		dispatcher.forward(request, response);
	}
	%>


	<div align="center">
		<br> <br>
		<div class="form-container">
			<form action="listAll" method="post">
				<input type="submit" value="Show Birthday List">
			</form>
			&nbsp;&nbsp;&nbsp;&nbsp;
			<form action="logout" method="post">
				<input type="submit" value="Logout">
			</form>
		</div>
		<br> <br>
		<form action="insert" method="post">
			Age: <input type="number" name="age" value=0 min="0" max="255"
				size="5"> Name: <input type="text" name="name" size="15"
				maxlength="40"> Birthday Month: <input type="number"
				name="month" min="1" max="12" size="4"> Birthday Day: <input
				type="number" name="day" min="1" max="31" size="4"> <input
				type="submit" value="Add">
		</form>
		<h2>${errorMessage}</h2>
		<br>
		<form action="sortBy" method="post">
			<label for="sortOptions"> Sorting Order: </label> <select
				name="sortOptions" id="sortOptions">
				<option value="sortByDefault">Default (id ▲)</option>
				<option value="sortByCountDownDays">Countdown (days) ▲</option>
				<option value="sortByCountDownDaysDesc">Countdown (days) ▼
				</option>
				<option value="sortByAge">Age ▲</option>
				<option value="sortByAgeDesc">Age ▼</option>
				<option value="sortByName">Name ▲</option>
				<option value="sortByNameDesc">Name ▼</option>
			</select> <input type="submit" value="Confirm">
		</form>
		<c:if test="${not empty order99}">
			<script>
				document
						.addEventListener(
								"DOMContentLoaded",
								function() {
									document.getElementById("sortOptions").value = "<c:out value='${order99}' />";
								});
			</script>
		</c:if>

	</div>
	<br>
	<table align="center">
		<tr>
			<th>id</th>
			<th>Name</th>
			<th>Age</th>
			<th>Birth Date</th>
			<th>Countdown (days)</th>
			<th>Next Birthday Date</th>
			<th>Actions</th>
		</tr>
		<c:if test="${not empty trackerList}">
			<c:forEach var="tracker" items="${trackerList}" varStatus="status">

				<tr>

					<td><c:out value="${status.index+1}" /></td>
					<td>${tracker.getName()}</td>
					<td>${tracker.getAge()}</td>
					<td>${tracker.getBirth_date()}</td>
					<td>${tracker.getCountdown_days()}</td>
					<td>${tracker.getBirthday_date()}</td>

					<td class="form-container">
						<form action="edit" method="post" id="edit_form_${status.index}">
							<input type="hidden" name="editId" value="${tracker.getId()}">
							<input type="hidden" name="editName" value="${tracker.getName()}">
							<input type="hidden" name="editAge" value="${tracker.getAge()}">
							<input type="hidden" name="editDate"
								value="${tracker.getBirth_date()}"> <input type="submit"
								value="Edit">
						</form> &nbsp;
						<form action="delete" method="post"
							id="delete_form_${status.index}">
							<input type="hidden" name="idDelete" value="${tracker.getId()}">
							<input type="submit" value="Delete">
						</form>
					</td>
				</tr>
			</c:forEach>
		</c:if>
	</table>
	</div>

</body>
</html>
