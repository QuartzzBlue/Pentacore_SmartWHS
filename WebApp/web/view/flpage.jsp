<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<!-- Custom Stylesheet -->
    <link rel="stylesheet" href="./plugins/chartist/css/chartist.min.css">
    <link rel="stylesheet" href="./plugins/chartist-plugin-tooltips/css/chartist-plugin-tooltip.css">
    <link href="css/style.css" rel="stylesheet">
    
 
</head>
<body>
	<div class="content-body">
		<div class="container-fluid mt-3">
	<div class="row">
		<div class="col-lg-12">
			<div class="card">
				<div class="card-body">
					<h4 class="card-title">Multi-line labels</h4>
					<div id="multi-line-chart" class="ct-chart ct-golden-section"></div>
				</div>
			</div>
		</div>
	</div>


	<div class="row">
		<!-- Table -->
		<div class="col-lg-12">
			<div class="card">
				<div class="card-body">
					<div class="active-member">
						<div class="table-responsive">
							<table class="table table-xs mb-0">
								<thead>
									<tr>
										<th>Forklift ID</th>
										<th>Warehouse ID</th>
										<th>Purchase Date</th>
										<th>Forklift Model</th>
										<th>Last Check Date</th>
										<th>Status</th>
										<th>Total Driven Distance</th>
									</tr>
								</thead>
								<tbody>
								<c:forEach var="fl" items="${fllist }" varStatus="status">
									<tr>
										<td>${fl.forkid }</td>
										<td>${fl.wareid}</td>
										<td><span>${fl.forkpurdate }</span></td>
										<td>${fl.forkmodel}</td>
										<td>${fl.forklastcheckdate}</td>
										<td></td>
										<td>${fl.forkdist}</td>
									</tr>
								</c:forEach>
								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	</div>
	</div>
	<script src="plugins/common/common.min.js"></script>
    <script src="js/custom.min.js"></script>
    <script src="js/settings.js"></script>
    <script src="js/gleek.js"></script>
    <script src="js/styleSwitcher.js"></script>

    <script src="./plugins/chartist/js/chartist.min.js"></script> 
    
    <script src="./plugins/chartist-plugin-tooltips/js/chartist-plugin-tooltip.min.js"></script>
    <!-- <script src="./js/plugins-init/chartist.init.js"></script> -->
    <script>

    
    //Multi-line labels
    
    new Chartist.Bar('#multi-line-chart', {
      labels: ['3 days ago', '2 days ago', 'yesterday', 'today!!'],
      series: [
        [60000, 40000, 80000, 70000],
        [40000, 30000, 70000, 65000],
        [8000, 3000, 10000, 6000]
      ]
    }, {
      seriesBarDistance: 10,
      axisX: {
        offset: 60
      },
      axisY: {
        offset: 80,
        labelInterpolationFnc: function(value) {
          return value + ' Km'
        },
        scaleMinSpace: 15
      },
      plugins: [
        Chartist.plugins.tooltip()
      ]
    });
    
    
    
    </script>

</body>
</html>