<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Door Check-Add Person</title>
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

.form-register {
	width: 100%;
	max-width: 330px;
	padding: 15px;
	margin: 0 auto;
}

.form-register .checkbox {
	font-weight: 400;
}

.form-register .form-control {
	position: relative;
	box-sizing: border-box;
	height: auto;
	padding: 10px;
	font-size: 16px;
}

.form-register .form-control:focus {
	z-index: 2;
}

.form-register input[type="email"] {
	margin-bottom: -1px;
	border-bottom-right-radius: 0;
	border-bottom-left-radius: 0;
}

.form-register input[type="password"] {
	margin-bottom: 10px;
	border-top-left-radius: 0;
	border-top-right-radius: 0;
}
</style>
</head>
<body class="text-center">
    
	<form action = "addPerson" class="form-register">
	    <h1 class="h1"><a href="home">SIGNE Building</a></h1>
	    <br/>
		<h1 class="h3 mb-3 font-weight-normal">Register Allowed Person</h1>
		<br/>
		<label for="inputEmail" class="sr-only ">DNI:</label>
        <input	type="text" name="dni" id="inputName" class="form-control" placeholder="write DNI" required autofocus>
		<br/>
		<label for="inputEmail" class="sr-only">Name:</label>
		<input	type="text" name="name" id="inputName" class="form-control" placeholder="write Name" required autofocus>
		<br/>
		<button class="btn btn-lg btn-primary btn-block" type="submit">Register</button>
		<br/>
		<p class="mt-5 mb-3 text-muted">&copy; 2019 Confirma Sistemas</p>
	</form>
	
	<script src="webjars/jquery/1.9.1/jquery.min.js"></script>
	<script src="webjars/bootstrap/3.3.6/js/bootstrap.min.js"></script>
</body>
</html>