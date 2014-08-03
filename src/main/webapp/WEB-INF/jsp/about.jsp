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
	<div class="navbar navbar-default navbar-fixed-top" role="navigation">
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
					<li><a href="<spring:url value="/" htmlEscape="true" />">Home</a></li>
					<li class="active"><a
						href="<spring:url value="/about" htmlEscape="true" />">About</a></li>
					<li><a href="<spring:url value="/admin" htmlEscape="true" />">Admin</a></li>
				</ul>
			</div>
			<!--/.nav-collapse -->
		</div>
	</div>

	<!-- Begin page content -->
	<div class="container marketing">
		<div class="page-header">
			<h1>About Us - What time could I run on another course?</h1>
		</div>
		<p class="lead">Enter your time and the course your ran it on, and
			we'll predict your time on other parkrun courses</p>
		<p>
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