<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width,initial-scale=1">
   
    <!-- Custom Stylesheet -->
    <link href="./plugins/bootstrap-material-datetimepicker/css/bootstrap-material-datetimepicker.css" rel="stylesheet">
    <!-- Page plugins css -->
    <link href="./plugins/clockpicker/dist/jquery-clockpicker.min.css" rel="stylesheet">
    <!-- Color picker plugins css -->
    <link href="./plugins/jquery-asColorPicker-master/css/asColorPicker.css" rel="stylesheet">
    <!-- Date picker plugins css -->
    <link href="./plugins/bootstrap-datepicker/bootstrap-datepicker.min.css" rel="stylesheet">
    <!-- Daterange picker plugins css -->
    <link href="./plugins/timepicker/bootstrap-timepicker.min.css" rel="stylesheet">
    <link href="./plugins/bootstrap-daterangepicker/daterangepicker.css" rel="stylesheet">

    <link href="css/style.css" rel="stylesheet">
    </head>
<body>
	<div class="content-body">
		<div class="container-fluid mt-3">

			<div class="row">
				<div class="col-lg-12">
					<div class="card">
						<div class="card-body">
							<div class="card-title">
								<h4>Product Register</h4>
							</div>
							<div class="table-responsive">
							<form name = "itemRegister" method="post" action="itemregister.pc">
								<table class="table">
									<thead>
										<tr>
											<th>Item ID</th>
											<th>Item Name</th>
											<th>Category</th>
											<th>Price</th>
											<th>Weight</th>
											<th>Qty</th>
											<th>Warehouse ID</th>
											<th>Warehouse Name</th>
										</tr>
									</thead>
									<tbody>
									
										<tr>
											<td><input type="text" class="form-control" id="itemid"
												name="itemid" placeholder="상품 ID"></td>
											<td><input type="text" class="form-control" id="itemname"
												name="itemname" placeholder="상품명" style="width: 115px;"></td>
											<td><select class="form-control" id="itemcate"
												name="itemcate" style="width: 115px;">
													<option value="">카테고리</option>
													<option value="전자제품">전자제품</option>
													<option value="차량부품">차량부품</option>

											</select></td>
											<td><input type="number" class="form-control" id="itemprice"
												name="itemprice" placeholder="단가"></td>
											<td><input type="number" class="form-control" id="itemweightpb"
												name="itemweightpb" placeholder="무게"></td>
											<td><input type="number" class="form-control" id="itemqtypb"
												name="itemqtypb" placeholder="개수"></td>
											<td><input type="text" class="form-control" id="wareid"
												name="wareid" placeholder="창고ID" readonly></td>
											<td><select class="form-control" id="warename"
												name="warename" style="width: 150px;" onchange="setWareID(this)">
													<option>창고명</option>
													<option value="ware00">이천 제1물류창고</option>
													<!--  <option value="ware01">CSS</option>-->

											</select></td>
										</tr>
										<tr>
											<td></td>
											<td></td>
											<td></td>
											<td></td>
											<td></td>
											<td></td>
											<td></td>
											<td><button type="button"
													class="btn mb-1 btn-primary btn-lg" style="width: 150px;">Register</button></td>
										</tr>
									</tbody>
								</table>
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>


			<div class="row">
				<div class="col-lg-12">
					<div class="card">
						<div class="card-body">
							<h4 class="card-title">Data Table</h4>
							<div class="table-responsive">
								<table class="table">
									<thead>
										<tr>
											<th>Product ID</th>
											<th>Product Name</th>
											<th>Category</th>
											<th>Price</th>
											<th>Weight</th>
											<th>Quantity</th>
											<th>Warehouse ID</th>
											<th>Warehouse Name</th>
										</tr>
									</thead>
									<tbody>
										<tr>
											<td><input type="text" class="form-control" id="#"
												name="#" placeholder="상품 ID"></td>
											<td><input type="text" class="form-control" id="#"
												name="#" placeholder="상품명"></td>
											<td><select class="form-control" id="val-skill"
												name="val-skill">
													<option value="">카테고리</option>
													<option value="html">HTML</option>
													<option value="css">CSS</option>

											</select></td>
											<td><input type="text" class="form-control" id="#"
												name="#" placeholder="단가"></td>
											<td><input type="text" class="form-control" id="#"
												name="#" placeholder="무게"></td>
											<td><input type="text" class="form-control" id="#"
												name="#" placeholder="개수"></td>
											<td><input type="text" class="form-control" id="#"
												name="#" placeholder="창고ID"></td>
											<td><select class="form-control" id="val-skill"
												name="val-skill" style="width: 150px;">
													<option value="">창고명</option>
													<option value="html">HTML</option>
													<option value="css">CSS</option>

											</select></td>
										</tr>
										<tr>
											<td></td>
											<td></td>
											<td></td>
											<td></td>
											<td></td>
											<td></td>
											<td></td>
											<td><button type="button"
													class="btn mb-1 btn-primary btn-lg" style="width: 150px;">Register</button></td>
										</tr>
									</tbody>
								</table>
							</div>
							<div class="table-responsive">
								<table class="table">
									<thead>
										<tr>
											<th>Product ID</th>
											<th>Product Name</th>
											<th>Category</th>
											<th>Price (KRW/box)</th>
											<th>Weight (KG/box)</th>
											<th>Quantity (per box)</th>
											<th>Warehouse Name</th>
											<th>Location</th>
											<th>Stock</th>
										</tr>
									</thead>
									<tbody>
										<tr>
											<td>Tiger Nixon</td>
											<td>System Architect</td>
											<td>Edinburgh</td>
											<td>61</td>
											<td>2011/04/25</td>
											<td>$320,800</td>
											<td>61</td>
											<td>2011/04/25</td>
											<td>$320,800</td>
										</tr>
										<tr>
											<td>Garrett Winters</td>
											<td>Accountant</td>
											<td>Tokyo</td>
											<td>63</td>
											<td>2011/07/25</td>
											<td>$170,750</td>
											<td>63</td>
											<td>2011/07/25</td>
											<td>$170,750</td>
										</tr>
										<tr>
											<td>Ashton Cox</td>
											<td>Junior Technical Author</td>
											<td>San Francisco</td>
											<td>66</td>
											<td>2009/01/12</td>
											<td>$86,000</td>
											<td>66</td>
											<td>2009/01/12</td>
											<td>$86,000</td>
										</tr>
										<tr>
											<td>Cedric Kelly</td>
											<td>Senior Javascript Developer</td>
											<td>Edinburgh</td>
											<td>22</td>
											<td>2012/03/29</td>
											<td>$433,060</td>
											<td>22</td>
											<td>2012/03/29</td>
											<td>$433,060</td>
										</tr>
										<tr>
											<td>Airi Satou</td>
											<td>Accountant</td>
											<td>Tokyo</td>
											<td>33</td>
											<td>2008/11/28</td>
											<td>$162,700</td>
											<td>33</td>
											<td>2008/11/28</td>
											<td>$162,700</td>
										</tr>
									</tbody>
								</table>
							</div>
						</div>
					</div>
				</div>
			</div>
			
			<div class="row">
				<div class="col-lg-12">
					<div class="card">
						<div class="card-body">
							<h4 class="card-title">Data Table</h4>
							<div class="table-responsive">
							<table class="table">
									<thead>
										<tr>
											<th>Product ID</th>
											<th>Product Name</th>
											<th>Warehouse ID</th>
											<th>Qty</th>
											<th>In/Ex-Warehouse</th>
										</tr>
									</thead>
									<tbody>
										<tr>
											<td><input type="text" class="form-control" id="#"
												name="#" placeholder="상품 ID"></td>
											<td><input type="text" class="form-control" id="#"
												name="#" placeholder="상품명"></td>
											<td><input type="text" class="form-control" id="#"
												name="#" placeholder="창고ID"></td>
											<td><input type="text" class="form-control" id="#"
												name="#" placeholder="개수"></td>
											<td><select class="form-control" id="val-skill"
												name="val-skill">
													<option value="">입/출고</option>
													<option value="In-W">In-Warehouse</option>
													<option value="Ex-W">Ex-Warehouse</option>
											</select></td>
										</tr>
										<tr>
											<td></td>
											<td></td>
											<td></td>
											<td></td>
											
											<td><button type="button"
													class="btn mb-1 btn-primary btn-lg" style="width: 150px;">Order</button></td>
										</tr>
									</tbody>
								</table>
								
							</div>
						</div>
					</div>
				</div>
			</div>
							

			<div class="row">
				<div class="col-lg-12">
					<div class="card">
						<div class="card-body">
							<h4 class="card-title">Data Table</h4>
							<div class="table-responsive">
								
								<table class="table">
									<thead>
										<tr>
											<th>Product ID</th>
											<th>Product Name</th>
											<th>Warehouse ID</th>
											<th>Qty</th>
											<th>In/Ex-Warehouse</th>
											<th>Start Date</th>
											<th>End Date</th>
										</tr>
									</thead>
									<tbody>
										<tr>
											<td><input type="text" class="form-control" id="#"
												name="#" placeholder="상품 ID"></td>
											<td><input type="text" class="form-control" id="#"
												name="#" placeholder="상품명"></td>
											<td><input type="text" class="form-control" id="#"
												name="#" placeholder="창고ID"></td>
											<td><input type="text" class="form-control" id="#"
												name="#" placeholder="개수"></td>
											<td><select class="form-control" id="val-skill"
												name="val-skill">
													<option value="">입/출고</option>
													<option value="In-W">In-Warehouse</option>
													<option value="Ex-W">Ex-Warehouse</option>
											</select></td>
											<td><div class="input-group"><input type="text" class="form-control mydatepicker" placeholder="mm/dd/yyyy"> <span class="input-group-append"><span class="input-group-text"><i class="mdi mdi-calendar-check"></i></span></span></div></td>
											<td><div class="input-group"><input type="text" class="form-control mydatepicker" placeholder="mm/dd/yyyy"> <span class="input-group-append"><span class="input-group-text"><i class="mdi mdi-calendar-check"></i></span></span></div></td>
											
										</tr>
										<tr>
											<td></td>
											<td></td>
											<td></td>
											<td></td>
											<td></td>
											<td></td>
											
											<td><button type="button"
													class="btn mb-1 btn-primary btn-lg" style="width: 150px;">Search</button></td>
										</tr>
									</tbody>
								</table>
								
								
							</div>
							
							<div class="table-responsive">
								<table class="table">
									<thead>
										<tr>
											<th>Product ID</th>
											<th>Product Name</th>
											<th>Warehouse Name</th>
											<th>Qty</th>
											<th>In/Ex Warehouse</th>
											<th>Date</th>
											
											
										</tr>
									</thead>
									<tbody>
										<tr>
											<td></td>
											<td></td>
											<td></td>
											<td></td>
											<td></td>
											<td></td>
										</tr>
										
									</tbody>
								</table>
							</div>
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
								<h4>Product list</h4>
							</div>
							<div class="table-responsive">
								<table class="table table-xs mb-0">
									<thead>
										<tr>
											<th>Product ID</th>
											<th>Product Name</th>
											<th>Category</th>
											<th>Price (KRW/box)</th>
											<th>Weight (KG/box)</th>
											<th>Quantity (per box)</th>
											<th>Warehouse Name</th>
											<th>Location</th>
											<th>Stock</th>
										</tr>
									</thead>
									<tbody>
										<tr>
											<td>Sarah Smith</td>
											<td>iPhone X</td>
											<td><span>United States</span></td>
											<td>
												<div>
													<div class="progress" style="height: 6px">
														<div class="progress-bar bg-success" style="width: 50%"></div>
													</div>
												</div>
											</td>
											<td><i class="fa fa-circle-o text-success  mr-2"></i>
												Paid</td>
											<td><span>Last Login</span> <!--<span class="m-0 pl-3">10 sec ago</span> -->
											</td>
											<td></td>
											<td></td>
											<td></td>
										</tr>

									</tbody>
								</table>
							</div>
						</div>
					</div>
				</div>
			</div>

			<div class="row">
				<div class="col-xl-3 col-lg-6 col-sm-6 col-xxl-6">

					<div class="card">
						<div class="chart-wrapper mb-4">
							<div class="px-4 pt-4 d-flex justify-content-between">
								<div>
									<h4>Sales Activities</h4>
									<p>Last 6 Month</p>
								</div>
								<div>
									<span><i class="fa fa-caret-up text-success"></i></span>
									<h4 class="d-inline-block text-success">720</h4>
									<p class=" text-danger">+120.5(5.0%)</p>
								</div>
							</div>
							<div>
								<canvas id="chart_widget_3"></canvas>
							</div>
						</div>
						<div class="card-body border-top pt-4">
							<div class="row">
								<div class="col-sm-6">
									<ul>
										<li>5% Negative Feedback</li>
										<li>95% Positive Feedback</li>
									</ul>
									<div>
										<h5>Customer Feedback</h5>
										<h3>385749</h3>
									</div>
								</div>
								<div class="col-sm-6">
									<div id="chart_widget_3_1"></div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="col-xl-3 col-lg-6 col-sm-6 col-xxl-6">
					<div class="card">
						<div class="card-body">
							<h4 class="card-title">Activity</h4>
							<div id="activity">
								<div class="media border-bottom-1 pt-3 pb-3">
									<img width="35" src="./images/avatar/1.jpg"
										class="mr-3 rounded-circle">
									<div class="media-body">
										<h5>Received New Order</h5>
										<p class="mb-0">I shared this on my fb wall a few months
											back,</p>
									</div>
									<span class="text-muted ">April 24, 2018</span>
								</div>
								<div class="media border-bottom-1 pt-3 pb-3">
									<img width="35" src="./images/avatar/2.jpg"
										class="mr-3 rounded-circle">
									<div class="media-body">
										<h5>iPhone develered</h5>
										<p class="mb-0">I shared this on my fb wall a few months
											back,</p>
									</div>
									<span class="text-muted ">April 24, 2018</span>
								</div>
								<div class="media border-bottom-1 pt-3 pb-3">
									<img width="35" src="./images/avatar/2.jpg"
										class="mr-3 rounded-circle">
									<div class="media-body">
										<h5>3 Order Pending</h5>
										<p class="mb-0">I shared this on my fb wall a few months
											back,</p>
									</div>
									<span class="text-muted ">April 24, 2018</span>
								</div>
								<div class="media border-bottom-1 pt-3 pb-3">
									<img width="35" src="./images/avatar/2.jpg"
										class="mr-3 rounded-circle">
									<div class="media-body">
										<h5>Join new Manager</h5>
										<p class="mb-0">I shared this on my fb wall a few months
											back,</p>
									</div>
									<span class="text-muted ">April 24, 2018</span>
								</div>
								<div class="media border-bottom-1 pt-3 pb-3">
									<img width="35" src="./images/avatar/2.jpg"
										class="mr-3 rounded-circle">
									<div class="media-body">
										<h5>Branch open 5 min Late</h5>
										<p class="mb-0">I shared this on my fb wall a few months
											back,</p>
									</div>
									<span class="text-muted ">April 24, 2018</span>
								</div>
								<div class="media border-bottom-1 pt-3 pb-3">
									<img width="35" src="./images/avatar/2.jpg"
										class="mr-3 rounded-circle">
									<div class="media-body">
										<h5>New support ticket received</h5>
										<p class="mb-0">I shared this on my fb wall a few months
											back,</p>
									</div>
									<span class="text-muted ">April 24, 2018</span>
								</div>
								<div class="media pt-3 pb-3">
									<img width="35" src="./images/avatar/3.jpg"
										class="mr-3 rounded-circle">
									<div class="media-body">
										<h5>Facebook Post 30 Comments</h5>
										<p class="mb-0">I shared this on my fb wall a few months
											back,</p>
									</div>
									<span class="text-muted ">April 24, 2018</span>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="col-xl-6 col-lg-12 col-sm-12 col-xxl-12">
					<div class="card">
						<div class="card-body">
							<h4 class="card-title mb-0">Store Location</h4>
							<div id="world-map" style="height: 470px;"></div>
						</div>
					</div>
				</div>
			</div>



			<div class="row">
				<div class="col-lg-3 col-sm-6">
					<div class="card">
						<div class="social-graph-wrapper widget-facebook">
							<span class="s-icon"><i class="fa fa-facebook"></i></span>
						</div>
						<div class="row">
							<div class="col-6 border-right">
								<div class="pt-3 pb-3 pl-0 pr-0 text-center">
									<h4 class="m-1">89k</h4>
									<p class="m-0">Friends</p>
								</div>
							</div>
							<div class="col-6">
								<div class="pt-3 pb-3 pl-0 pr-0 text-center">
									<h4 class="m-1">119k</h4>
									<p class="m-0">Followers</p>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="col-lg-3 col-sm-6">
					<div class="card">
						<div class="social-graph-wrapper widget-linkedin">
							<span class="s-icon"><i class="fa fa-linkedin"></i></span>
						</div>
						<div class="row">
							<div class="col-6 border-right">
								<div class="pt-3 pb-3 pl-0 pr-0 text-center">
									<h4 class="m-1">89k</h4>
									<p class="m-0">Friends</p>
								</div>
							</div>
							<div class="col-6">
								<div class="pt-3 pb-3 pl-0 pr-0 text-center">
									<h4 class="m-1">119k</h4>
									<p class="m-0">Followers</p>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="col-lg-3 col-sm-6">
					<div class="card">
						<div class="social-graph-wrapper widget-googleplus">
							<span class="s-icon"><i class="fa fa-google-plus"></i></span>
						</div>
						<div class="row">
							<div class="col-6 border-right">
								<div class="pt-3 pb-3 pl-0 pr-0 text-center">
									<h4 class="m-1">89k</h4>
									<p class="m-0">Friends</p>
								</div>
							</div>
							<div class="col-6">
								<div class="pt-3 pb-3 pl-0 pr-0 text-center">
									<h4 class="m-1">119k</h4>
									<p class="m-0">Followers</p>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="col-lg-3 col-sm-6">
					<div class="card">
						<div class="social-graph-wrapper widget-twitter">
							<span class="s-icon"><i class="fa fa-twitter"></i></span>
						</div>
						<div class="row">
							<div class="col-6 border-right">
								<div class="pt-3 pb-3 pl-0 pr-0 text-center">
									<h4 class="m-1">89k</h4>
									<p class="m-0">Friends</p>
								</div>
							</div>
							<div class="col-6">
								<div class="pt-3 pb-3 pl-0 pr-0 text-center">
									<h4 class="m-1">119k</h4>
									<p class="m-0">Followers</p>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
			<!-- / container-fluid mt-3 -->
		<!-- #/ container -->
	</div>
	

    <!--**********************************
        Scripts
    ***********************************-->
    <script src="plugins/common/common.min.js"></script>
    <script src="js/custom.min.js"></script>
    <script src="js/settings.js"></script>
    <script src="js/gleek.js"></script>
    <script src="js/styleSwitcher.js"></script>

    <script src="./plugins/moment/moment.js"></script>
    <script src="./plugins/bootstrap-material-datetimepicker/js/bootstrap-material-datetimepicker.js"></script>
    <!-- Clock Plugin JavaScript -->
    <script src="./plugins/clockpicker/dist/jquery-clockpicker.min.js"></script>
    <!-- Color Picker Plugin JavaScript -->
    <script src="./plugins/jquery-asColorPicker-master/libs/jquery-asColor.js"></script>
    <script src="./plugins/jquery-asColorPicker-master/libs/jquery-asGradient.js"></script>
    <script src="./plugins/jquery-asColorPicker-master/dist/jquery-asColorPicker.min.js"></script>
    <!-- Date Picker Plugin JavaScript -->
    <script src="./plugins/bootstrap-datepicker/bootstrap-datepicker.min.js"></script>
    <!-- Date range Plugin JavaScript -->
    <script src="./plugins/timepicker/bootstrap-timepicker.min.js"></script>
    <script src="./plugins/bootstrap-daterangepicker/daterangepicker.js"></script>

    <script src="./js/plugins-init/form-pickers-init.js"></script>
    
    <script>
    	var wareIdList = ["wh1111", "#"];
    	var target = document.getElementById("wareid");
   	 	function setWareID(w) {
   	 		if(w.value == "ware00") target.value = wareIdList[0];
   	 		else target.value= "";
   	 	}
    
    </script>
    </body>
</html>