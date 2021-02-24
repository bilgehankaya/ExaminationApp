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
</head>
<body>
	<nav class="navbar navbar-expand-lg navbar-light bg-light">
		<div class="sidebar-brand-icon rotate">
			<i class="fab fa-xing fa-2x ml-2"></i>
			<a class="navbar-brand ml-2" href="${pageContext.request.contextPath}/part/home">eeXam</a>
		</div>
		
		<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarTogglerDemo02" aria-controls="navbarTogglerDemo02" aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>

		<div class="collapse navbar-collapse" id="navbarTogglerDemo02">
			<ul class="navbar-nav mr-auto mt-2 mt-lg-0">
			<li class="nav-item active">
				<a class="nav-link" href="#">Home <span class="sr-only">(current)</span></a>
			</li>
			<li class="nav-item">
				<a class="nav-link" href="#">Link</a>
			</li>
			</ul>
			<form:form action="${pageContext.request.contextPath}/logout" method="POST" class="form-inline my-2 my-lg-0">
				<button class="btn btn-success my-2 my-sm-0" type="submit">Logout</button>
			</form:form>
		</div>
	</nav>

	<div class="container">
		<div class="row">
			<div class="col-12 align-items-center mb-4 mt-4">
				<h5 class="text-info mb-0">ENROLLED EXAMS</h5>
			</div>

			<c:forEach var="result" items="${participantDto.resultDtos}">
				<div class="col-md-4 col-xl-4">
					<div class="card">
						<div class="card-body">
							<h4 class="card-title text-dark">${result.examTitle}</h4>
							<hr>
							<div class="row">
								<div class="col-md-6 col-xl-6">
									<div class="font-weight-bold text-m mb-1">
										<span>Status: </span><span class="font-weight-normal">${result.joined}</span>
									</div>
								</div>
								<c:if test = "${result.joined}">
									<div class="col-md-6 col-xl-6">
										<div class="font-weight-bold text-m mb-1">
											<span>Correct Number:<br></span><span class="font-weight-normal">${result.correctNumber}</span>
										</div>
									</div>
									<div class="col-md-6 col-xl-6">
										<div class="font-weight-bold text-m mb-1">
											<span>Incorrect Number:<br></span><span class="font-weight-normal">${result.incorrectNumber}</span>
										</div>
									</div>
									<div class="col-md-6 col-xl-6">
										<div class="font-weight-bold text-m mb-1">
											<span>Empty Number:<br></span><span class="font-weight-normal">${result.emptyNumber}</span></div>
									</div>
								</c:if>
								<c:if test = "${!result.joined}">
									<a href="${pageContext.request.contextPath}/part/start/${result.examId}">
										<h4 class="card-title text-dark">Take the exam</h4>
									</a>
								</c:if>
							</div>
						</div>
					</div>
				</div>
			</c:forEach>
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