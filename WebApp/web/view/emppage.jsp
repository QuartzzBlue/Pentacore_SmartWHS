<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width,initial-scale=1">
<style>
.card-title {
	display: flex;
	text-align: center;
	align-items: baseline;
}

.card-title div button {
	color: #E9EAEC;
	opacity: 0.1; /* Opacity (Transparency) */
	color: rgba(0, 0, 0, 0.5); /* RGBA Color (Alternative Transparency) */
	-webkit-filter: blur(2px);
}
</style>
</head>
<body>
	<div class="content-body">
		<div class="container-fluid mt-3">
			<!--<div class="row">
				<div class="col-lg-12">
					<div class="card">
						<div class="card-body">
							<h4 class="card-title">Multi-line labels</h4>
							<div id="multi-line-chart" class="ct-chart ct-golden-section"></div>
						</div>
					</div>
				</div>
			</div>  -->
			<div class="row">
				<div class="col-lg-4">
					<div class="card">
						<div class="card-body">
							<div class="card-title">
								<h4>Donut Chart - 창고 별 직원 수</h4>
							</div>
							<hr></hr>
							<div id="morris-donut-chart"></div>
						</div>
					</div>
				</div>
				<div class="col-lg-8">
					<div class="card">
						<div class="card-body">
							<h4 class="card-title">Visit Chart</h4>
							<div id="morris-area-chart0"></div>
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<!-- Table -->
				<div class="col-lg-12">
					<div class="card">
						<div class="card-body">
							<div class="card-title">
								<h4>Employee List</h4>
								<div data-toggle="tooltip" data-placement="right"
									title="직원 조회 테이블입니다.">
									<button class="fas fa-question-circle"></button>
								</div>
								
									<button type="button" id="addEmpBtn" class="btn mb-1 btn-info" data-toggle="modal" data-target="#addEmp" data-remote="view/modal/addEmp.jsp">Add Employee<span class="btn-icon-right"><i class="fa fa-check"></i></span></button>
								
							</div>
							<hr></hr>
							<div class="table-responsive">
								<table id="empListBody"
									class="table table-striped table-bordered">
									<thead id="empListTHead">
										<tr>
											<th>ID</th>
											<th>Name</th>
											<th>Position</th>
											<th>Email</th>
											<th>Phone</th>
											<th>Warehouse ID</th>
											<th>Warehouse Name</th>
										</tr>
									</thead>
									<tbody id="empListTBody">

									</tbody>
								</table>

								<!-- emp 추가 -->
								<div class="modal fade" id="addEmp" tabindex="-1" role="dialog">
									<div class="modal-dialog modal-lg" role="document">
										<div class="modal-content">
											<!-- <input type="hidden" id="selectedInvId"> 
											<input type="hidden" id="selectedInvDate"> -->
											<div class="modal-header">
												<h5 class="modal-title">Employee Register</h5>
												<button type="button" class="close" data-dismiss="modal"
													aria-label="Close">
													<span aria-hidden="true">&times;</span>
												</button>
											</div>
											<div class="modal-body">
												

											</div>
											<div class="modal-footer">
												<button type="button" class="btn btn-primary"
													data-dismiss="modal">Close</button>
											</div>
										</div>
									</div>
								</div>

								<!-- emp 수정 -->
								<div class="modal fade" id="modifyEmp" tabindex="-1"
									role="dialog">
									<div class="modal-dialog modal-lg" role="document">
										<div class="modal-content">
											<!-- <input type="hidden" id="selectedInvId"> 
											<input type="hidden" id="selectedInvDate"> -->
											<div class="modal-header">
												<h5 class="modal-title">Employee Info Modification</h5>
												<button type="button" class="close" data-dismiss="modal"
													aria-label="Close">
													<span aria-hidden="true">&times;</span>
												</button>
											</div>
											<div class="modal-body"></div>
											<div class="modal-footer">
												<!-- <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button> -->
												<button type="button" class="btn btn-primary"
													data-dismiss="modal">Close</button>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<!-- table end -->
			</div>
		</div>
	</div>

	<script src="./plugins/raphael/raphael.min.js"></script>
	<script src="./plugins/morris/morris.min.js"></script>
	<script>
		var getEmpList = function() {

			var empTable = $('#empListBody').DataTable({

				ajax : {
					url : 'getemplist.pc',
					dataSrc : '',
				},
				columns : [ {
					data : 'empno'
				}, {
					data : 'empname'
				}, {
					data : 'empjob'
				}, {
					data : 'empemail'
				}, {
					data : 'empphone'
				}, {
					data : 'wareid'
				}, {
					data : 'warename'
				} ]
			});

		};
		
		$('#addEmp').on('show.bs.modal', function(e) {

			var button = $(e.relatedTarget);
			var modal = $(this);

			modal.find('.modal-body').load(button.data("remote"));
			

		});
/*
		$(document).on("mouseenter", "#empListTBody", function() {
			$('#empListTBody tr').addClass("selectedEmp");
			$('#empListTBody tr').attr('data-toggle', "modal");
			$('#empListTBody tr').attr('data-target', "#empCrud");
			//$('#empListTBody tr').attr('data-remote', "view/modal/empCrud.jsp");

		});*/

		var drawDonutChart = function(data) {

			console.log(data);
			Morris.Donut({
				element : 'morris-donut-chart',
				data : data,
				resize : true,
				colors : [ '#4d7cff', '#7571F9', '#9097c4' ]
			});

		};

		/*
		$("#addEmpBtn").on(click : function() {
							
		});
		*/
		
		
		$(document).ready(
				function() {
					history.replaceState({}, null, location.pathname);
					getEmpList();

					//donut chart 그리기
					$.ajax({
						type : "post",
						url : "getempnumbywh.pc",
						dataType : "text",
						contentType : "application/json; charset=UTF-8",
						success : function(response) {

							var data = $.parseJSON(response);
							drawDonutChart(data);

						},
						error : function(request, status, error) {
							console.log("code:" + request.status + "\n"
									+ "message:" + request.responseText + "\n"
									+ "error:" + error);
						}
					});

				});
	</script>

</body>
</html>