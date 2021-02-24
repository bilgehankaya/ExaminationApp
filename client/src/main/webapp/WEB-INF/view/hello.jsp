<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
    <h2>HelloWorld!!</h2>
    <form:form action="${pageContext.request.contextPath}/logout" method="POST">
        <button type="submit" class="dropdown-item">
            <i class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-dark">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Logout</i>
        </button>
    </form:form>
</body>
</html>
