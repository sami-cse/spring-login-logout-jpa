<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<title></title>
<%@include file="./includes/header.jsp"%>
<%@include file="./includes/security.jsp"%>
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
	<h3>
		<a href="${contextPath}/logout" role="button" class="btn btn-primary">Logout</a>
		<%@include file="./includes/navigation.jsp"%>
		<a href="${contextPath}/create" role="button" class="btn btn-primary">Create
			New User</a> <a href="${contextPath}/userPermissions" role="button"
			class="btn btn-primary">Create User's Permission</a> <a
			href="${contextPath}/userPermissionLists" role="button"
			class="btn btn-primary">User's Permission Lists</a>
	</h3>
	<div class="panel-body">
		<form:form method="post" role="form" commandName="permission"
			action="${contextPath}${action}" class="form-horizontal">
			<div class="form-group">
				<div class="col-sm-6 col-sm-offset-3">
					<form:input path="UrlTitle" class="form-Control"
						placeholder="Url Title" />
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-6 col-sm-offset-3">
					<form:input path="url" class="form-Control" placeholder="Url" />
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-6 col-sm-offset-3">
					<form:select class="form-control" path="user.id">
						<form:options items="${userLists}" itemLabel="name" itemValue="id"></form:options>
					</form:select>
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-offset-3 col-sm-6">
					<button type="submit" class="btn btn-primary btn-block">&nbsp;
						Save &nbsp;</button>
					&nbsp; &nbsp;
				</div>
			</div>
		</form:form>
	</div>
</div>