<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/bootstrap/css/bootstrap.min.css">
	<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/fonts/fontawesome-all.min.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/fonts/font-awesome.min.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/fonts/fontawesome5-overrides.min.css">
	<style>
		.error {color:red; font-size: 15px;}
	</style>
</head> 

<body class="bg-primary">
	<div class="container">
        <div class="row justify-content-center">
            <div class="col-md-10 col-lg-9 col-xl-8">
                <div class="card shadow-lg o-hidden border-0 my-5">
                    <div class="card-body p-0">
                        <div class="row">
                            <div class="col-lg-12">
                                <div class="p-5">
                                    <div class="text-center">
                                        <h4 class="text-dark mb-4">Create Your Account!</h4>
                                    </div>
                                    <form:form action="${pageContext.request.contextPath}/register/process" method="POST" class="user" modelAttribute="user">
										<div class="form-group">
											<div class="col-xs-15">
												<div>	
													<c:if test="${registrationError != null}">
														<div class="alert alert-danger col-xs-offset-1 col-xs-10">
															${registrationError}
														</div>
													</c:if>
												</div>
											</div>
										</div>
                                        <div class="form-group">
											<form:input path="username" class="form-control form-control-user" placeholder="Username" />
											<form:errors path="username" cssClass="error" />
										</div>
										<div class="row">
											<div class="col-md-12 col-lg-6 col-xl-6">
												<div class="form-group">
													<form:password path="password" class="form-control form-control-user" placeholder="Password" />
													<form:errors path="password" cssClass="error" />
												</div>
											</div>
											<div class="col-md-12 col-lg-6 col-xl-6">
												<div class="form-group">
													<form:password path="confirmPassword" class="form-control form-control-user" placeholder="Confirm Password" />
													<form:errors path="confirmPassword" cssClass="error" />
												</div>
											</div>
										</div>
										<div class="form-group">
											<form:input path="firstName" class="form-control form-control-user" placeholder="First Name" />
											<form:errors path="firstName" cssClass="error" />
										</div>
										<div class="form-group">
											<form:input path="lastName" class="form-control form-control-user" placeholder="Last Name" />
											<form:errors path="lastName" cssClass="error" />
										</div>
										<div class="form-group">
											<form:input path="phoneNumber" class="form-control form-control-user" placeholder="Phone Number" />
											<form:errors path="phoneNumber" cssClass="error" />
										</div>
										<div class="form-group">
											<form:input path="email" class="form-control form-control-user" placeholder="Email" />
											<form:errors path="email" cssClass="error" />
										</div>
										<div class="form-group pl-1">
											<form:radiobutton path="role" value="PARTICIPANT" /><label>&nbsp;&nbsp;I am Participant</label><br>
											<form:radiobutton path="role" value="INSTRUCTOR" /><label>&nbsp;&nbsp;I am Instructor</label><br>
											<form:errors path="role" cssClass="error" />
										</div>
                                        <div class="form-group">
											<button class="btn btn-primary btn-block text-white btn-user" type="submit">Login</button>
                                        </div>
                                    </form:form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
   
   	<script src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/bootstrap/js/bootstrap.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/chart.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/bs-init.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-easing/1.4.1/jquery.easing.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/theme.js"></script>
</body>

</html>