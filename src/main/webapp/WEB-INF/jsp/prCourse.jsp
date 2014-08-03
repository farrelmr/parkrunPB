<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>Parkrun Predictor</title>

<!-- Bootstrap core CSS -->
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/assets/bootstrap/3.1.1/css/bootstrap.min.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/assets/bootstrap/3.1.1/css/bootstrap-theme.min.css" />

<!-- Custom styles for this template -->
<style type="text/css">
/* Sticky footer styles
-------------------------------------------------- */
html {
	position: relative;
	min-height: 100%;
}

body {
	/* Margin bottom by footer height */
	margin-bottom: 60px;
}

#footer {
	position: absolute;
	bottom: 0;
	width: 100%;
	/* Set the fixed height of the footer here */
	height: 60px;
	background-color: #f5f5f5;
}

/* Custom page CSS
-------------------------------------------------- */
/* Not required for template or sticky footer method. */
body>.container {
	padding: 60px 15px 0;
}

.container .text-muted {
	margin: 20px 0;
}

#footer>.container {
	padding-right: 15px;
	padding-left: 15px;
}

code {
	font-size: 80%;
}

.table td {
	text-align: center;
}

.table th {
	text-align: center;
}

td div.left {
	text-align: left;
}

.error {
	color: #ff0000;
}

.errorblock {
	color: #000;
	background-color: #ffEEEE;
	border: 3px solid #ff0000;
	padding: 8px;
	margin: 16px;
}
</style>

<!-- Just for debugging purposes. Don't actually copy this line! -->
<!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->

<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>

<body>

	<!-- Fixed navbar -->
	<div class="navbar navbar-default navbar-fixed-top">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target=".navbar-collapse">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="#">Parkrun Predictor</a>
			</div>
			<div class="collapse navbar-collapse">
				<ul class="nav navbar-nav">
					<li class="active"><a
						href="<spring:url value="/" htmlEscape="true" />">Home</a></li>
					<li><a href="<spring:url value="/about" htmlEscape="true" />">About</a></li>
					<li><a href="<spring:url value="/admin" htmlEscape="true" />">Admin</a></li>
				</ul>
			</div>
			<!--/.nav-collapse -->
		</div>
	</div>

	<!-- Begin page content -->
	<div class="container marketing">
		<div class="page-header">
			<h1>Predict my time?</h1>
		</div>
		<p class="lead">Enter your time and the course your ran it on, and
			we'll predict your time on other parkrun courses</p>
		<p>

			<form:form method="post" action="predict.html"
				class="form-inline text-center" commandName="prPredict" role="form">

				<form:errors path="*" cssClass="errorblock" element="div" />

				<!-- form class="form-inline text-center" role="form" -->
				<div class="form-group">
					<label class="sr-only" for="mmTime">Time (mm)</label>
					<form:input type="text" path="mm" maxlength="2" size="2"
						class="form-control input-lg" placeholder="mm" />
					:

				</div>
				<div class="form-group">
					<label class="sr-only" for="ssTime">Time (ss)</label>
					<form:input type="text" class="form-control input-lg" id="ssTime"
						path="ss" maxlength="2" size="2" placeholder="ss" />
					&nbsp;
				</div>

				<div class="form-group">

					<form:select path="courseId" class="form-control input-lg">
						<form:options items="${prCourseMap}" />
					</form:select>

				</div>
			&nbsp;
			
				<input type="submit" value="Predict  My Time"
					class="btn btn-default btn-lg" />

			</form:form>

		</p>

		<p>


			<c:if test="${prPredictBoolean == true}">

				<table class="table table-condensed">
					<thead>
						<tr>
							<th>Parkrun Course</th>
							<th>Region</th>
							<th>Predicted Time</th>
						</tr>
					</thead>
					<tbody>

						<c:forEach items="${prCourseList}" var="prCourse">

							<tr>
								<td><div class="left">
										<a href="${prCourse.url}">${prCourse.prName}</a>
									</div></td>
								<td>${prCourse.prregion.regionName}</td>
								<td>${prCourse.estimatedAverageTime}</td>
							</tr>

						</c:forEach>

					</tbody>
				</table>
			</c:if>
		</p>
	</div>

	<div id="footer">
		<div class="container">
			<p class="text-muted">
				Parkrun Predictor from <a href="http://glenware.wordpress.com">http://glenware.wordpress.com</a>.
			</p>
		</div>
	</div>


	<!-- Bootstrap core JavaScript
    ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<!-- script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script-->
	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/assets/jquery/1.11.0/jquery.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/assets/bootstrap/3.1.1/js/bootstrap.min.js"></script>

</body>
</html>