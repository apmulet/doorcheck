<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Door Check-Add Person</title>
<link href="webjars/bootstrap/3.3.6/css/bootstrap.min.css"
	rel="stylesheet">
<link href="webjars/datatables/1.10.19/css/dataTables.bootstrap.min.css"
	rel="stylesheet">
<link rel="stylesheet" type="text/css"
	href="js/jquery.datetimepicker.min.css" />
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
	min-height: 0px !important;
	padding: 0px;
}

tr.estancias td {
	padding: 0px !important;
}
</style>

<script language="JavaScript">

	function dateToMillisUTC(		
			dateField){
	
	    var txt = $(dateField).val();
		if ((!txt)||(txt.length==0))
			return 0;// not selected
			
	    var d = $(dateField).datetimepicker('getValue');		
		 
		return d.getTime();
	}

	function dateToString(milis) {
		if (milis == 0)
			return "-";
		var d = new Date(milis);
		return d.toLocaleString("es-Es");
	}

	function elapseTimeToString(startTime, endTime) {
		if (startTime == 0) {
			if (endTime==0) return "-";
			// caso afuera 
			startTime = endTime;
			endTime = Date.now();
		} else if (endTime == 0) {
			// caso dentro
			endTime = Date.now();
		}

		var timediff = endTime - startTime;
		timediff = timediff / 1000;
		var seconds = Math.floor(timediff % 60);
		timediff = timediff / 60;
		var minutes = Math.floor(timediff % 60);
		timediff = timediff / 60;
		var hours = Math.floor(timediff % 24);
		var days = Math.floor(timediff / 24);
		return days + ' days, ' + hours + ' hours, ' + minutes + ' mins, '
				+ seconds + ' secs';
	}

	var table;

	function initDatatable() {
		$(document)
				.ready(
						function() {
							table = $('#tablelist')
									.DataTable(
											{
												pageLength : 20,
												lengthChange : false,
												processing : true,
												serverSide : true,

												columns : [
														{
															"data" : "person.name",
															"searchable" : false,
															"orderable" : false,
															"width" : "200"
														},
														{
															"data" : "person.dni",
															"searchable" : false,
															"orderable" : false
														},
														{
															"data" : "enter",
															"searchable" : false,
															"orderable" : false,
															"width" : "100",
															render : function(d) {
																return dateToString(d);
															}
														},
														{
															"data" : "exit",
															"searchable" : false,
															"orderable" : false,
															"width" : "100",
															render : function(d) {
																return dateToString(d);
															}
														},
														{
															"searchable" : false,
															"orderable" : false,
															"width" : "200",
															data : function(
																	row, type) {
																return elapseTimeToString(
																		row.enter,
																		row.exit);
															}
														} ],
												ajax : {
													contentType : 'application/json',
													url : "dtlist/",
													type : "post",
													data : function(d) {
														d.name = $("#name").val();
														d.dateStart = dateToMillisUTC('#startDate');
														d.dateEnds = dateToMillisUTC('#endsDate');
														d.searchType = $("#type").val();
														var s = JSON.stringify(d);
														return s;
													},
													error : function(xhr,
															error, thrown) {
														console.log(error);
													}
												// ,success : function(a, b, c) {
												// 		console.log(a);
												// }
												},
												"dataSrc" : function(json) {
													return json.data;
												},
												searching : false
											})
						});
	}

	 
	function initDatePickers() {
		var conf = {
			formatDate : 'd/m/Y H:i',
			format : 'd/m/Y H:i',
		};
		$('#startDate').datetimepicker(conf);
		$('#endsDate').datetimepicker(conf);
	}

	function init() {
		initDatatable();
		initDatePickers();
	}

	function clearSearch() {
		$('#startDate').datetimepicker('reset');
		$('#endsDate').datetimepicker('reset');
		$('#name').val('');
	}

	/** buscar solo si se presiona el boton no en change */
	function doSearch() {
		table.draw();
	}
	
</script>
</head>





<body class="text-center">
	<div style="width: 800px;">
		<h1 class="h1">
			<a href="home">SIGNE Building</a>
		</h1>
		<h2>Filtered Accesses List</h2>
		<div>
			<br />
			<div>
				<label>Search:</label> <select id="type" name="type">
					<option value="inside">inside</option>
					<option value="outside">outside</option>
					<option value="history">history</option>
				</select> <label>Name:</label> <input type="text" id="name" /> <label>Start
					Date:</label> <input type="text" id="startDate" /> <label>End
					Date:</label> <input type="text" id="endsDate" />
				<button type="button" onclick="clearSearch();">Clear</button>
				<button type="button" onclick="doSearch();">Search</button>
			</div>
		</div>


		<table id="tablelist" class="table table-sm">
			<thead>
				<th scope="col">Name</th>
				<th scope="col">dni</th>
				<th scope="col">enter</th>
				<th scope="col">exit</th>
				<th scope="col">elapsed</th>
			</thead>
			<tbody id="listcont">
			</tbody>
		</table>
	</div>
	<p class="mt-5 mb-3 text-muted">&copy; 2019 Confirma Sistemas</p>
	</div>

	<script src="webjars/jquery/1.9.1/jquery.min.js"></script>
	<script src="webjars/bootstrap/3.3.6/js/bootstrap.min.js"></script>
	<script src="webjars/datatables/1.10.19/js/jquery.dataTables.js"></script>
	<script src="webjars/datatables/1.10.19/js/dataTables.bootstrap.min.js"></script>
	<script src="js/jquery.datetimepicker.full.js"></script>
	<script>
		init();
	</script>



</body>
</html>