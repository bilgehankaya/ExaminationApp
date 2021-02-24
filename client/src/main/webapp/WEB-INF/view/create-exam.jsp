<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>

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
			<a class="navbar-brand ml-2" href="${pageContext.request.contextPath}/inst/home">eeXam</a>
		</div>
		
		<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarTogglerDemo02" aria-controls="navbarTogglerDemo02" aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>

		<div class="collapse navbar-collapse" id="navbarTogglerDemo02">
			<ul class="navbar-nav mr-auto mt-2 mt-lg-0">
			<li class="nav-item active">
				<a class="nav-link" href="${pageContext.request.contextPath}/inst/exam">Create Exam<span class="sr-only">(current)</span></a>
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


    <form:form action="" method="POST" modelAttribute="examdto">
        <div class="container" id="questionDiv">
            <button onclick="setAttr();" type="submit" class="btn btn-success my-3">Post Exam</button>
            <div class="examProps">

                <div class="form-group">
                    <strong>Exam Title</strong>
                    <form:input id="examTitle" path="examdto.title" value="My Exam"
                        class="form-control form-control-user containsString" />
                </div>
            </div>
        </div>
    </form:form>




    <div class="container" id="newQuestion">
        <button onclick="createQuestion();" class="btn btn-lg btn-success">Create Question</button>
    </div>

    <script>
        const questionChoices = {}; // number of choices are stored for question
        const questionAnswers = {}; // answer of question are stored

        function addChoice(questionID) {

        if (questionChoices[questionID] < 5) {
            questionChoices[questionID] = (questionChoices[questionID] + 1);
            console.log(`Choices: ${questionChoices[questionID]}`);
            choiceID = getRandomInt(9999999);
            let choiceTags = `<div id="choice${choiceID}" class="input-group mt-2">
                <form:input id="input${choiceID}" path="" isItAnswer="false" type="text" class="form-control choiceOf${questionID} isItAnswer${questionID}" />
                <div class="btn-group form-group w-13 pl-2">
                <button id="delChoice${choiceID}" onclick="removeChoice('${choiceID}', '${questionID}');" class="btn-danger btn-lg fa fa-times">
                <button id="selectChoice${choiceID}" onclick="answerGreen('${choiceID}','${questionID}');" class="btn-success btn-lg fa fa-check ml-2 hide${questionID}">
                </div> 
            </div>`

            $(`#choices${questionID}`).append(choiceTags);
        } else {
            alert("You can add maximum 5 choices to the question!");
        }
        }

        function removeChoice(choiceID, questionID) {
        $(`#choice${choiceID}`).remove();
        console.log("Choice is removed!");
        questionChoices[questionID] = (questionChoices[questionID] - 1);
        if (questionAnswers[questionID] === choiceID) {
            questionAnswers[questionID] = null;
        }
        }

        function answerGreen(choiceID, questionID) {
        $(`.hide${questionID}`).show();
        $(`.isItAnswer${questionID}`).css('background', '#FFFFFF');
        $(`#selectChoice${choiceID}`).hide();
        $(`#input${choiceID}`).css('background', 'rgba(0, 128, 0, 0.6)');
        questionAnswers[questionID] = choiceID;
        $(`.isItAnswer${questionID}`).attr("isItAnswer", "false");
        $(`#input${choiceID}`).attr("isItAnswer", "true");
        
        }

        function createQuestion() {
        questionID = getRandomInt(9999999);
        const questionTags = `
        <div id="question${questionID}" class="py-5">
            <div class="form-group">
            <strong>Question Content</strong>
            <form:input id="content${questionID}" path="" value="2 + 2 = ?" class="form-control form-control-user containsString"/>
            </div>
            <div class="form-group">
            <strong>Points: </strong>
            <form:input id="point${questionID}" path="" value="10" class="form-control form-control-user containsString"/>
            </div>
            <div class="form-group">
            <form:input type="hidden" id="correct${questionID}" path="" value="" class="form-control form-control-user containsString"/>
            </div>
            <div id="choices${questionID}">
            </div>
            <button id="addChoice" onclick="addChoice('${questionID}');" class="btn btn-warning btn-block text-white btn-user mt-3" type="button">Add choice</button>
            <button id="delQuestion" onclick="deleteQuestion('${questionID}')" class="btn btn-danger btn-block text-white btn-user" type="button">Delete Question</button
        </div>`

        $("#questionDiv").append(questionTags);

        if (!questionChoices.hasOwnProperty(questionID))
            questionChoices[questionID] = 0;
        }

        function deleteQuestion(questionID) {
        $(`#question${questionID}`).remove();
        delete questionChoices[questionID];
        console.log("Question is removed!");
        }

        function getRandomInt(max) {
        return Math.floor(Math.random() * Math.floor(max));
        }

        function getChoices(questionID, questionNumber) {
        $(`.choiceOf${questionID}`).each((index, item) => {
            $(item).attr('path', `examdto.questions[${questionNumber}].choices[${index}].content`);
            console.log($(item).attr("isItAnswer"))

            if( $(item).attr("isItAnswer") === "true")
            $(`#correct${questionID}`).attr("value", index+1);
        })
        }

        function getQuestionProps(index, questionID) {
        $(`#content${questionID}`).attr('path', `examdto.questions[${index}].content`);
        $(`#point${questionID}`).attr('path', `examdto.questions[${index}].point`);
        $(`#correct${questionID}`).attr('path', `examdto.questions[${index}].correctChoice`);
        getChoices(questionID, index);
        }

        function setAttr() {
        (Object.entries(questionChoices)).forEach((element, index) => {
            getQuestionProps(index, element[0]);
        });
        }
    </script>

    <script src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/bootstrap/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/chart.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/bs-init.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-easing/1.4.1/jquery.easing.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/theme.js"></script>

</body>

</html>