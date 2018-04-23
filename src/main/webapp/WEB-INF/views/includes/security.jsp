<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:choose>
	<c:when test="${empty permissions}">
		<c:redirect url="http://localhost:8080/spring-login-logout-jpa/404" />
	</c:when>
</c:choose>
