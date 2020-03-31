<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width,initial-scale=1">
<title>Admin Dashboard</title>
<!-- Favicon icon -->
<link rel="icon" type="image/png" sizes="16x16"
	href="images/favicon.png">
<!-- Pignose Calender -->
<link href="./plugins/pg-calendar/css/pignose.calendar.min.css"
	rel="stylesheet">
<!-- Chartist -->
<link rel="stylesheet" href="./plugins/chartist/css/chartist.min.css">
<link rel="stylesheet"
	href="./plugins/chartist-plugin-tooltips/css/chartist-plugin-tooltip.css">
<!-- Custom Stylesheet -->
<link href="css/style.css" rel="stylesheet">


</head>

<body>

	<!--*******************
        Preloader start
    ********************-->
	<div id="preloader">  
		<div class="loader">
			<svg class="circular" viewBox="25 25 50 50">
                <circle class="path" cx="50" cy="50" r="20" fill="none"
					stroke-width="3" stroke-miterlimit="10" />
            </svg>
		</div>
	</div>
	<!--*******************
        Preloader end
    ********************-->


	<!--**********************************
        Main wrapper start
    ***********************************-->
	<div id="main-wrapper">

		<!--**********************************
            Nav header start
        ***********************************-->
        
		<div class="nav-header">
			<div class="brand-logo">
				<a href="index.html"> <b class="logo-abbr"><img
						src="images/logo.png" alt=""> </b> <span class="logo-compact"><img
						src="./images/logo-compact.png" alt=""></span> <span
					class="brand-title"> <img src="images/logo-text.png" alt="">
				</span>
				</a>
			</div>
		</div>
		
		<!--**********************************
            Nav header end
        ***********************************-->

		<!--**********************************
            Header start
        ***********************************-->
		<div class="header">
			<div class="header-content clearfix">

				<div class="nav-control">
					<div class="hamburger">
						<span class="toggle-icon"><i class="icon-menu"></i></span>
					</div>
				</div>
				
				<!-- 
				<div class="header-left">
					<div class="input-group icons">
						<div class="input-group-prepend">
							<span
								class="input-group-text bg-transparent border-0 pr-2 pr-sm-3"
								id="basic-addon1"><i class="mdi mdi-magnify"></i></span>
						</div>
						<input type="search" class="form-control"
							placeholder="Search Dashboard" aria-label="Search Dashboard">
						<div class="drop-down animated flipInX d-md-none">
							<form action="#">
								<input type="text" class="form-control" placeholder="Search">
							</form>
						</div>
					</div>
				</div>
				 -->
				 
				 <!--**********************************
		            Sidebar start <메뉴!!!>
		        ***********************************-->
				<div class="nk-sidebar">
				
					<div class="nk-nav-scroll">
						<ul class="metismenu cf" id="menu">
							<!-- <li class="nav-label">Dashboard</li> -->
							<li>
								<!-- 상품 입출고 --> 
								<a href="pdpage.hc"
								aria-expanded="false"> <i class="icon-note menu-icon"></i><span
									class="nav-text">Product Management</span>
							</a>
								 <!-- <ul aria-expanded="false">
		                            <li><a href="./index.html">Home 1</a></li>
		                             <li><a href="./index-2.html">Home 2</a></li> 
		                        </ul> -->
							</li>
							<li>
								<!-- 지게차 관리 --> 
								<a href="flpage.hc"
								aria-expanded="false"> <i class="icon-speedometer menu-icon"></i><span
									class="nav-text">Forklift Management</span>
							</a>
							</li>
							<li>
								<!-- 솔루션 --> 
								<a href="solpage.hc"
								aria-expanded="false"> <i class="icon-globe-alt menu-icon"></i><span
									class="nav-text">Solution</span>
							</a>
							</li>
						</ul>
					</div>
				</div>
				<!--**********************************
		            Sidebar end
		        ***********************************-->
				 
				 
				<div class="header-right">
					<ul class="clearfix">
						<!-- 메일박스 -->
						<li class="icons dropdown"><a href="javascript:void(0)"
							data-toggle="dropdown"> <i class="mdi mdi-email-outline"></i>
								<!-- <span class="badge badge-pill gradient-1">3</span> -->
							</a>
						</li>
						<!-- 알림 -->
						<li class="icons dropdown"><a href="javascript:void(0)"
							data-toggle="dropdown"> <i class="mdi mdi-bell-outline"></i>
								<!-- <span class="badge badge-pill gradient-2">3</span> -->
							</a>
						</li>
						<!-- 개인 아이콘 -->
						<li class="icons dropdown">
							<div class="user-img c-pointer position-relative"
								data-toggle="dropdown">
								<span class="activity active"></span> <img
									src="images/user/1.png" height="40" width="40" alt="">
							</div>
							<div
								class="drop-down dropdown-profile animated fadeIn dropdown-menu">
								<div class="dropdown-content-body">
									<ul>
										<li><a href="app-profile.html"><i class="icon-user"></i>
												<span>Profile</span></a></li>
										<li><a href="javascript:void()"> <i
												class="icon-envelope-open"></i> <span>Inbox</span>
												<div class="badge gradient-3 badge-pill gradient-1">3</div>
										</a></li>

										<hr class="my-2">
										<li><a href="page-lock.html"><i class="icon-lock"></i>
												<span>Lock Screen</span></a></li>
										<li><a href="page-login.html"><i class="icon-key"></i>
												<span>Logout</span></a></li>
									</ul>
								</div>
							</div>
						</li>
					</ul>
				</div>
			</div>
		</div>
		<!--**********************************
            Header end ti-comment-alt
        ***********************************-->
		
		

		<!--**********************************
            Content body start <Center>
        ***********************************-->
		<div class="content-body">
			<div class="container-fluid mt-3">
				<c:choose>
					<c:when test="${center == null }">
						<jsp:include page="pdpage.jsp" />
					</c:when>
					<c:otherwise>
						<jsp:include page="${center }.jsp" />
					</c:otherwise>

				</c:choose>
			</div>
			<!-- / container-fluid mt-3 -->
			<!-- #/ container -->
		</div>
		<!--**********************************
            Content body end
        ***********************************-->


		<!--**********************************
            Footer start
        ***********************************-->
		<div class="footer">
			<div class="copyright">
				<p>
					Copyright &copy; Designed & Developed by <a
						href="https://themeforest.net/user/quixlab">Quixlab</a> 2018
				</p>
			</div>
		</div>
		<!--**********************************
            Footer end
        ***********************************-->
	</div>
	<!--**********************************
        Main wrapper end
    ***********************************-->

	<!--**********************************
        Scripts
    ***********************************-->
	<script src="plugins/common/common.min.js"></script>
	<script src="js/custom.min.js"></script>
	<script src="js/settings.js"></script>
	<script src="js/gleek.js"></script>
	<script src="js/styleSwitcher.js"></script>

	<!-- Chartjs -->
	<script src="./plugins/chart.js/Chart.bundle.min.js"></script>
	<!-- Circle progress -->
	<script src="./plugins/circle-progress/circle-progress.min.js"></script>
	<!-- Datamap -->
	<script src="./plugins/d3v3/index.js"></script>
	<script src="./plugins/topojson/topojson.min.js"></script>
	<script src="./plugins/datamaps/datamaps.world.min.js"></script>
	<!-- Morrisjs -->
	<script src="./plugins/raphael/raphael.min.js"></script>
	<script src="./plugins/morris/morris.min.js"></script>
	<!-- Pignose Calender -->
	<script src="./plugins/moment/moment.min.js"></script>
	<script src="./plugins/pg-calendar/js/pignose.calendar.min.js"></script>
	<!-- ChartistJS -->
	<script src="./plugins/chartist/js/chartist.min.js"></script>
	<script
		src="./plugins/chartist-plugin-tooltips/js/chartist-plugin-tooltip.min.js"></script>

	<!-- JQeury -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
	<script src="./js/dashboard/dashboard-1.js"></script>

</body>

</html>