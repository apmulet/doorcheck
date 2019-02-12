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

.form-dni {
	width: 100%;
	max-width: 330px;
	padding: 15px;
	margin: 0 auto;
}

.form-dni .checkbox {
	font-weight: 400;
}

.form-dni .form-control {
	position: relative;
	box-sizing: border-box;
	height: auto;
	padding: 10px;
	font-size: 16px;
}

.form-dni .form-control:focus {
	z-index: 2;
}

.form-dni input[type="text"] {
	margin-bottom: -1px;
	border-bottom-right-radius: 0;
	border-bottom-left-radius: 0;
}
</style>
<script language="JavaScript">

   function showResp(rsp){
	   var cls = "alert alert-success";
	   if (rsp.startsWith("Error")) cls = "alert alert-danger";
	   if (rsp.startsWith("Warning")) cls = "alert alert-warning";
	   $("#resp").removeClass().addClass(cls).text(rsp);
   }

 	function enter() {
		var dni = $("#inputDNI").val();
		$.ajax({
			url : 'enter/' + dni,
			success : function(content) {
				showResp(content)
			}
		});
	}

	function exit() {
		var dni = $("#inputDNI").val();
		$.ajax({
			url : 'exit/' + dni,
			success : function(content) {
				showResp(content)
			}
		});
	}
 </script>

</head>
<body class="text-center">

	<form class="form-dni">
		<h1 class="h1"><a id="homelnk" href="home">SIGNE Building</a></h1>
		<br />
		<h3 class="h3 mb-3 font-weight-normal">Checking Door </h3>
		<br /> <label for="inputEmail" class="sr-only">DNI:</label>
		<input type="text" name="dni" id="inputDNI" class="form-control" placeholder="write DNI" required autofocus />
		<br /> 
		<div id="resp"></div>
		
		<A id ="enterbt" class="btn btn-primary btn-block" href="#" onclick="enter();">Enter</A>
		<A id ="exitbt" class="btn btn-primary btn-block" href="#" onclick="exit();">Exit</A>
		<p class="mt-5 mb-3 text-muted">&copy; 2019 Confirma Sistemas</p>
	</form>

	<script src="webjars/jquery/1.9.1/jquery.min.js"></script>
	<script src="webjars/bootstrap/3.3.6/js/bootstrap.min.js"></script>

	 
</body>
</html>