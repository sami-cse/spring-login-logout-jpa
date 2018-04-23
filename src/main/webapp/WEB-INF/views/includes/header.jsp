
<%
	response.setHeader("Cache-Control", "no-cache");
	response.setHeader("Cache-Control", "no-store");
	response.setHeader("Pragma", "no-cache");
	response.setDateHeader("Expires", 0);
	if (session.getAttribute("userId") == null) {
		String redirectURL = "http://localhost:8080/spring-login-logout-jpa/";
		response.sendRedirect(redirectURL);
	}
%>


