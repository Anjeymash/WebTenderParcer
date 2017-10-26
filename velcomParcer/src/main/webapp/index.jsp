<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>




<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>index</title>


<!-- Bootstrap -->
<link href="css/bootstrap.css" rel="stylesheet">


</head>

</head>


<body>


	<div class="navbar navbar-inverse">


		<div class="container">


			<div class="row">

				<div class="navbar-header">
					Tender Service
					<form method="post" action="Controller">
						<input type="hidden" name="command" value="parsertender" /> <input
							type="text" class="form-default" name="tenderNom" value=""
							placeholder="№ tender" required="required" />
						<button type="submit">Parse</button>
					</form>

				</div>
			</div>

			<c:if test="${not empty requestScope.tender }">
				<table>

					<tr>
						<th>Наименование</th>
						<td>${requestScope.tender.title}</td>
					</tr>
					<tr>
						<th>Рубрика</th>
						<td>${requestScope.tender.industry}</td>
					</tr>
					<tr>
						<th>Начало</th>
						<td>${requestScope.tender.start}</td>
					</tr>
					<tr>
						<th>Завершение</th>
						<td>${requestScope.tender.end}</td>
					</tr>
					<tr>
						<th>Е-мэил</th>
						<td>${requestScope.tender.eMail}</td>
					</tr>
					<tr>
						<th>Контакты</th>
						<td>${requestScope.tender.contacts}</td>
					</tr>



				</table>

			</c:if>
		</div>
		<div class="container">
			<div class="row">

				<form method="post" action="Controller">
					<input type="hidden" name="command" value="savetodb" />
					<button type="submit" class="btn btn-primary">Save to DB</button>
				</form>


				<form method="post" action="Controller">
					<input type="hidden" name="command" value="createxml" />
					<button type="submit" class="btn btn-success">Create XML</button>
				</form>

				<form method="post" action="Controller">
					<input type="hidden" name="command" value="createcsv" />
					<button type="submit" class="btn btn-success">Create CSV</button>
				</form>

				<c:if test="${not empty requestScope.errorMessage }">
					<div class="alert alert-warning">
						<c:out value="${requestScope.errorMessage}"></c:out>
					</div>
				</c:if>
				<c:if test="${not empty requestScope.message }">
					<div class="alert alert-success">
						<c:out value="${requestScope.message}"></c:out>
					</div>
				</c:if>
			</div>
		</div>
	</div>
</body>

</html>

