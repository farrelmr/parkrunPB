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

<title>Parkrun Predictor - Admin</title>

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
				<a class="navbar-brand" href="#">Parkrun Predictor - Admin</a>
			</div>
			<div class="collapse navbar-collapse">
				<ul class="nav navbar-nav">
					<li><a href="<spring:url value="/" htmlEscape="true" />">Home</a></li>
					<li><a href="<spring:url value="/about" htmlEscape="true" />">About</a></li>
					<li class="active"><a
						href="<spring:url value="/admin" htmlEscape="true" />">Admin</a></li>
				</ul>
			</div>
			<!--/.nav-collapse -->
		</div>
	</div>

	<!-- Begin page content -->
	<div class="container marketing">
		<div class="page-header">
			<h1>Course Administration</h1>
		</div>

		<form:form method="post" action="addAdmin.html" commandName="prCourse"
			role="form" class="form-horizontal">

			<form:errors path="*" cssClass="errorblock" element="div" />

			<div class="form-group">
				<label class="col-sm-2 control-label" for="prName">Course
					Name</label>
				<div class="col-sm-4">
					<form:input type="text" path="prName" class="form-control" />
				</div>
			</div>

			<div class="form-group">
				<label class="col-sm-2 control-label" for="url">Link</label>
				<div class="col-sm-4">
					<form:input type="text" path="url" class="form-control" />
				</div>
			</div>

			<div class="form-group">
				<label class="col-sm-2 control-label" for="averageTime">Average
					Time(s)</label>
				<div class="col-sm-4">
					<form:input type="text" path="averageTime" class="form-control" />
				</div>
			</div>

			<div class="form-group">
				<label class="col-sm-2 control-label" for="regionId">Course</label>
				<div class="col-sm-4">
					<form:select path="regionId" class="form-control">
						<form:options items="${prRegionMap}" />
					</form:select>
				</div>
			</div>

			<div class="form-group">
				<div class="col-sm-2"></div>
				<div>
					<input type="submit"
						value="<spring:message code="label.addparkrun"/>"
						class="btn btn-primary btn-lg" />
				</div>
			</div>

		</form:form>


		<p>
		<div class="page-header">
			<h1>Parkruns</h1>
		</div>

		<c:if test="${!empty prCourseList}">

			<table class="table table-condensed">
				<thead>
					<tr>
						<th>ID</th>
						<th>PRName</th>
						<th>URL</th>
						<th>averagetime</th>
						<th>&nbsp;</th>
					</tr>
				</thead>
				<tbody>

					<c:forEach items="${prCourseList}" var="prCourse">
						<tr>
							<td>${prCourse.id}</td>
							<td><div class="left">${prCourse.prName}</div></td>
							<td><div class="left">${prCourse.url}</div></td>
							<td><div class="left">${prCourse.averageTimeString}</div></td>
							<td><a href="deleteAdmin/${prCourse.id}">delete</a></td>
						</tr>
					</c:forEach>

				</tbody>
			</table>
		</c:if>
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