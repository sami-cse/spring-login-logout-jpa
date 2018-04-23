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
	</h3>
	<div class="panel-body">
		<form:form method="post" role="form" commandName="user"
			action="${contextPath}${action}" class="form-horizontal">
			<div class="form-group">
				<div class="col-sm-6 col-sm-offset-3">
					<form:input path="name" class="form-Control" placeholder="Add Name" />
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-6 col-sm-offset-3">
					<form:input path="password" class="form-Control"
						placeholder="Add Password" />
				</div>
			</div>
			<%-- <div class="form-group">
				<div class="col-sm-6 col-sm-offset-3">
					<form:select class="form-control" path="role.id">
						<form:options items="${roleList}" itemLabel="name" itemValue="id"></form:options>
					</form:select>
				</div>
			</div> --%>
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