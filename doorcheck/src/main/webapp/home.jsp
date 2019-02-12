<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Door Check</title>
<link href="webjars/bootstrap/3.3.6/css/bootstrap.min.css"
	rel="stylesheet">
	<style>
html, body {
	height: 100%;
}

body {
	display: -ms-flexbox;
	display: -webkit-box;
	display: flex;
	-ms-flex-align: center;
	-ms-flex-pack: center;
	-webkit-box-align: center;
	align-items: center;
	-webkit-box-pack: center;
	justify-content: center;
	padding-top: 40px;
	padding-bottom: 40px;
	background-color: #f5f5f5;
}
 
</style>
</head>
<body class="text-center">
	<div style="width: 400px">
		<h1 class="h1">
			<a href="home">SIGNE Building</a>
		</h1>
		<h2> Door Check Options</h2>
		<A class="btn btn-primary btn-block" id="regbt" href="register">Register Person</A>
		<br/>
		<A class="btn btn-primary btn-block" id="doorchkbt" href="doorcheck">Door Check</A>
		<br/>
		<A class="btn btn-primary btn-block" id="listbt" href="list">List Filters</A>
		<br/>
		<p class="mt-5 mb-3 text-muted">&copy; 2019 Confirma Sistemas</p>
	</div>
	<script src="webjars/bootstrap/3.3.6/js/bootstrap.min.js"></script>
</body>
</html>