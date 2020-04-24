<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style>
.card-title{
	display: flex;
	text-align: center;
	align-items: baseline;
}
.card-title div button{
	color: #E9EAEC;
	opacity: 0.1;                /* Opacity (Transparency) */
    color: rgba(0, 0, 0, 0.5);   /* RGBA Color (Alternative Transparency) */
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
				<!-- Table -->
				<div class="col-lg-12">
					<div class="card">
						<div class="card-body">
							<div class="card-title">
								<h4>Employee List</h4>
								<div data-toggle="tooltip" data-placement="right" title="직원 조회 테이블입니다.">
									<button class="fas fa-question-circle"></button>
								</div>
							</div>
							<hr></hr>
							<div class="table-responsive">
								<table id="empListBody" class="table table-striped table-bordered">
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
							</div>
						</div>
					</div>
				</div>
				<!-- table end -->
			</div>
		</div>
	</div>
	<script>
		var getEmpList = function(){
			
			var empTable = $('#empListBody').DataTable({
				
				ajax : {
					url : 'getemplist.pc',
					dataSrc : '',
				},columns : [ 
					{data : 'empno'}, 
					{data : 'empname'}, 
					{data : 'empjob'}, 
					{data : 'empemail'},
					{data : 'empphone'},
					{data : 'wareid'},
					{data : 'warename'}
				]
			});
		};
		
		$(document).ready(function() {
			getEmpList();
			history.replaceState({}, null, location.pathname);
		});
	</script>

</body>
</html>