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
										<th>Forklist ID</th>
										<th>Status</th>
										<th>Last Check Date</th>
										<th>Total Driven Distance</th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<td>1234</td>
										<td>Working</td>
										<td><span>2019/08/12</span></td>
										<td>26Km</td>
									</tr>

								</tbody>
							</table>
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
    <script src="./js/plugins-init/chartist.init.js"></script>
</body>
</html>