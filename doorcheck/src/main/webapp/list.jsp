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
	
	-webkit-box-pack: center;
	justify-content: center;
	padding-top: 40px;
	padding-bottom: 40px;
	background-color: #f5f5f5;
}

#listcont {
	text-align: left;
}


tr.estancias {
   min-height:0px !important;
   padding:0px;
}

tr.estancias td {
   padding:0px !important;
   
}
 
        
</style>

<script language="JavaScript">

	function list(type) {
		$("#listcont").empty();
		$.ajax({
			url : 'list/' + type,
			success : function(content) {
				renderPersons(content)
			}
		})
	}
	
	function listEstancias(dni) {
		var $container = $("#sub_" + dni);
		$container.empty()
		$.ajax({
			url : 'estancias/' + dni,
			success : function(content) {
				renderEstancias($container,content);
			}
		})

	}

	function renderPersons(persons) {
		var $cont = $("#listcont");
		var now = Date.now();
		$.each(persons, function(index, p) {
			var inside = (p.lastEnterDate > 0);
			var status = (inside ? "Inside" : "Outside");
			var since = (inside)?p.lastEnterDate:p.lastExitDate;
			var sinceStr = (since==0)?"":dateToString(since);
			var elapsed = (since==0)?"":elapseTimeToString(since,now);
			var rclss = inside ? "table-warning" : "";
			$cont.append('<tr class=""'+rclss+'"><td>' + p.name + '</td><td>' + p.dni + '</td><td class="'+rclss+'">' + status + '</td><td>' + sinceStr + '</td><td>' + elapsed + '</td><td><a href="#" class="btn btn-outline-info" onclick="listEstancias(\''+p.dni+'\');">+</a></td></tr>');
			$cont.append('<tr class="estancias"><td id="sub_'+p.dni+'" colspan="6" ></td></tr>');
		});
	}
	
	function renderEstancias($container,estancias) {
		var now = Date.now();
		var $table = $('<table class="table table-sm"><tr><thead><th>from</th><th>until</th><th>elapsed<th></thead></table>');
		var $tbody = $('<tbody></tbody>');
		$.each(estancias, function(index, e) {
			var enter = e.enter;
			var exit = e.exit;
			var elapsed =  elapseTimeToString(enter,exit);
			$tbody.append('<tr><td>' + dateToString(enter) + '</td><td>' + dateToString(exit) + '</td><td>' + elapsed + '</td></tr>');
		});
		$table.append($tbody);
		$container.append($table);
	}

	function dateToString(milis) {
		var d = new Date(milis);
		return d.toLocaleString();
	}

	function elapseTimeToString(startTime, endTime) {
		var timediff = endTime - startTime;
		timediff = timediff / 1000;
		var seconds = Math.floor(timediff % 60);
		timediff = timediff / 60;
		var minutes = Math.floor(timediff % 60);
		timediff = timediff / 60;
		var hours = Math.floor(timediff % 24);
		var days = Math.floor(timediff / 24);
		return days + ' days, ' + hours + ' hours, ' + minutes
				+ ' mins' + seconds + ' secs';
	}

	
</script>
</head>





<body class="text-center">
	<div style="width: 800px;">
		<h1 class="h1">
			<a href="home">SIGNE Building</a>
		</h1>
		 
		<h2>Filtered Accesses List </h2>
		 

		<div class="btn-group" role="group" aria-label="Basic example">
			<button type="button" class="btn btn-secondary"
				onclick="list('all');">All</button>
			<button type="button" class="btn btn-secondary"
				onclick="list('inside');">InSide</button>
			<button type="button" class="btn btn-secondary"
				onclick="list('outside');">OutSide</button>
		</div>

		<div>
			<br />
			<table class="table table-sm">
				<thead>
					<th scope="col">Name</th>
					<th scope="col">DNI</th>
					<th scope="col">status</th>
					<th scope="col">since</th>
					<th scope="col">elapsed</th>
					<th scope="col">+</th>
				</thead>
				<tbody id="listcont">
				</tbody>
			</table>
		</div>
		<p class="mt-5 mb-3 text-muted">&copy; 2019 Confirma Sistemas</p>
	</div>

	<script src="webjars/jquery/1.9.1/jquery.min.js"></script>
	<script src="webjars/bootstrap/3.3.6/js/bootstrap.min.js"></script>
</body>
</html>