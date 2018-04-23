<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%
	if (session.getAttribute("userId") != null) {
		String redirectURL = "http://localhost:8080/spring-login-logout-jpa/users";
		response.sendRedirect(redirectURL);
	}
%>




<title></title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<div class="panel panel-success">
	<div class="panel-body">
		<h2>${errors}</h2>
		<form:form method="post" role="form" commandName="user"
			action="${contextPath}${action}" class="form-horizontal"
			onsubmit="return validateLoginForm()" name="loginForm" id="form">
			<input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" />
			<div class="form-group">
				<div class="col-sm-6 col-sm-offset-3">
					<form:input path="id" id="id" class="form-Control"
						placeholder="User Id" />
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-6 col-sm-offset-3">
					<form:input path="name" id="name" class="form-Control"
						placeholder="Add Name" />
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-6 col-sm-offset-3">
					<form:input path="password" id="password" class="form-Control"
						placeholder="Add Password" />
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-offset-3 col-sm-6">
					<button type="submit" class="btn btn-primary">&nbsp; Login
						&nbsp;</button>
					<input type="button" class="btn btn-primary" id="btn" value="Reset">
				</div>
			</div>
		</form:form>
	</div>
</div>


<script>
	function validateLoginForm() {
		var id = document.forms["loginForm"]["id"].value;
		var name = document.forms["loginForm"]["name"].value;
		var password = document.forms["loginForm"]["password"].value;
		if (id == "") {
			window.alert("ID must be filled out");
			return false;
		}
		if (name == "") {
			window.alert("Name must be filled out");
			return false;
		}
		if (password == "") {
			window.alert("Password must be filled out");
			return false;
		}
	}

	$(document).ready(function() {
		$("#btn").click(function() {
			$("#form")[0].reset();
		});
	});
</script>



