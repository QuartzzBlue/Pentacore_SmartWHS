<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width,initial-scale=1">


<style>
.badge-danger {
	cursor: pointer;
}
</style>
</head>


<body>

	<div class="content-body">
		<div class="container-fluid mt-3">



			<!-- ************* (1) *************** -->
			<div class="row">
				<div class="col-lg-12">
					<div class="card">
						<div class="card-body">
							<div class="card-title">
								<h4>Product Register</h4>
							</div>
							<div class="table-responsive">
								<div class="form-validation">
									<form name="itemRegister" method="post"
										action="itemregister.pc">
										<table class="table">
											<thead>
												<tr>
													<th>Item ID<span class="text-danger">*</span></th>
													<th>Item Name<span class="text-danger">*</span></th>
													<th>Category<span class="text-danger">*</span></th>
													<th>Price<span class="text-danger">*</span></th>
													<th>Weight<span class="text-danger">*</span></th>
													<th>Qty<span class="text-danger">*</span></th>
													<th>Warehouse ID<span class="text-danger">*</span></th>
													<th>Warehouse Name<span class="text-danger">*</span></th>
												</tr>
											</thead>
											<tbody>

												<tr>
													<td><input type="text" class="form-control"
														id="itemid" name="itemid" placeholder="상품 ID"></td>
													<td><input type="text" class="form-control"
														id="itemname" name="itemname" placeholder="상품명"
														style="width: 115px;"></td>
													<td><select class="form-control" id="itemcate"
														name="itemcate" style="width: 115px;">
															<option value="">카테고리</option>
															<option value="전자제품">전자제품</option>
															<option value="차량부품">차량부품</option>

													</select></td>
													<td><input type="number" class="form-control"
														id="itemprice" name="itemprice" placeholder="단가"></td>
													<td><input type="number" class="form-control"
														id="itemweightpb" name="itemweightpb" placeholder="무게"></td>
													<td><input type="number" class="form-control"
														id="itemqtypb" name="itemqtypb" placeholder="개수"></td>
													<td><input type="text" class="form-control"
														id="wareid_R" name="wareid" placeholder="창고ID" readonly></td>
													<td><select class="form-control" id="warename"
														name="warename" style="width: 150px;"
														onchange="setWareID(this)">
															<option>창고명</option>
															<option value="ware00_R">이천 제1물류창고</option>
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
													<td><button type="submit"
															class="btn mb-1 btn-primary btn-lg"
															id="toastr-success-top-right" style="width: 150px;">Register</button></td>
												</tr>
											</tbody>
										</table>
									</form>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>

			<!-- ************* (2) *************** -->
			<div class="row">
				<div class="col-lg-12">
					<div class="card">
						<div class="card-body">
							<h4 class="card-title">Data Table</h4>

							<div class="table-responsive">
								<table
									class="table table-striped table-bordered zero-configuration dataTable">

									<thead>
										<tr>
											<th>Item ID</th>
											<th>Item Name</th>
											<th>Category</th>
											<th>Price (KRW/box)</th>
											<th>Weight (KG/box)</th>
											<th>Qty (per box)</th>
											<th>Warehouse ID</th>
											<th>Warehouse Name</th>
											<th>Location</th>
											<th>Stock</th>
										</tr>
									</thead>

									<tbody id="itListBody">
										<!--<c:forEach var="it" items="${itemList }">
											<tr class="selectedItList">
												<td>${it.itemid}</td>
												<td>${it.itemname}</td>
												<td>${it.itemcate}</td>
												<td>${it.itemprice}</td>
												<td>${it.itemweightpb}</td>
												<td>${it.itemqtypb}</td>
												<td>${it.wareid}</td>
												<td>${it.warename}</td>
												<td>${it.itemloc}</td>
												<td>${it.itemstock}</td>
											</tr>
										</c:forEach>-->
									</tbody>
								</table>
							</div>
						</div>
					</div>
				</div>
			</div>

			<!-- ************* (3) *************** -->
			<div class="row">
				<div class="col-lg-12">
					<div class="card">
						<div class="card-body">
							<h4 class="card-title">Data Table</h4>
							<div class="table-responsive">
								<!-- (추가) 서버에 올려보낼 때, 로그인id(empno)도 같이 올려 보내야함 -->
								<table class="table">
									<thead>
										<tr>
											<th>Item ID<span class="text-danger">*</span></th>
											<th>Item Name<span class="text-danger">*</span></th>
											<th>Warehouse ID<span class="text-danger">*</span></th>
											<th>Warehouse Name<span class="text-danger">*</span></th>
											<th>Qty<span class="text-danger">*</span></th>
											<th>Status<span class="text-danger">*</span></th>
											<th>
										</tr>
									</thead>
									<tbody>
										<tr id="setItInfo">
											<td><input type="text" class="form-control" id="itemid"
												name="itemid" placeholder="상품 ID"></td>
											<td><input type="text" class="form-control"
												id="itemname" name="itemname" placeholder="상품명"></td>
											<td><input type="text" class="form-control" id="wareid"
												name="wareid" placeholder="창고ID"></td>
											<td><input type="text" class="form-control"
												id="warename" name="warename" placeholder="창고명"></td>
											<td><input type="text" class="form-control"
												id="invoicedtlqty" name="invoicedtlqty" placeholder="개수"
												style="width: 80px;"></td>
											<!-- (추가)출고 시 현재 재고보다 많은 양을 선택하면 에러 띄워야함 -->
											<td><select class="form-control" id="invoicestat"
												name="invoicestat">
													<option value="">입/출고</option>
													<option value="Receiving">Receiving</option>
													<option value="Shipping">Shipping</option>
											</select></td>


											<!-- ----------Modal-------- -->

											<td><input type="text" class="form-control" id="itemloc"
												name="itemloc"></td>
											<td><a href="#itemLocModal" class="btn btn-primary"
												data-toggle="modal" data-target="#theModal"
												data-remote="/WebApp/view/modal/itemLoc.jsp">선택</a>
												<div class="modal fade" id="theModal" tabindex="-1" role="dialog">
													<div class="modal-dialog" role="document">
														<div class="modal-content">
															<div class="modal-header">
																<h5 class="modal-title" id="exampleModalLabel">New
																	message</h5>
																<button type="button" class="close" data-dismiss="modal"
																	aria-label="Close">
																	<span aria-hidden="true">&times;</span>
																</button>
															</div>
															<div class="modal-body">
																
															</div>
															<div class="modal-footer">
																<button type="button" class="btn btn-secondary"
																	data-dismiss="modal">Close</button>
																<button type="button" class="btn btn-primary">Send
																	message</button>
															</div>
														</div>
													</div>
												</div></td>
											<!-- Modal End -->




											<td><button type="button" id="addItemToInvoice"
													class="btn mb-1 btn-primary btn-lg">Add</button>
											<td>
										</tr>
									</tbody>
								</table>
							</div>

							<div class="table-responsive">
								<!-- <form name="itemRegister" method="post"
									action="invoiceregister.pc"> -->
								<table class="table">
									<thead>
										<tr>
											<th></th>
											<th>Item ID</th>
											<th>Item Name</th>
											<th>Warehouse ID</th>
											<th>Warehouse Name</th>
											<th>Qty</th>
											<th>Status</th>
											<th></th>
										</tr>
									</thead>
									<tbody id="invoiceDetail">

									</tbody>
								</table>

								<!-- </form>  -->
							</div>
						</div>
					</div>
				</div>
			</div>

			<!-- ************* (4) *************** -->
			<div class="row">
				<div class="col-lg-12">
					<div class="card">
						<div class="card-body">
							<h4 class="card-title">Invoice Search -> AJAX로 바꾸고 Invoice
								검색 후에 클릭시 detail 출력</h4>

							<div class="table-responsive">
								<form name="itemRegister" method="post"
									action="invoicesearch.pc">
									<table class="table">
										<thead>
											<tr>
												<th>Item ID</th>
												<th>Item Name</th>
												<th>Warehouse ID</th>
												<th>Status</th>
												<th>Start Date</th>
												<th>End Date</th>
											</tr>
										</thead>
										<tbody>
											<tr>
												<td><input type="text" class="form-control" id="itemid"
													name="itemid" placeholder="상품 ID"></td>
												<td><input type="text" class="form-control"
													id="itemname" name="itemname" placeholder="상품명"></td>
												<td><input type="text" class="form-control" id="wareid"
													name="wareid" placeholder="창고ID"></td>
												<td><select class="form-control" id="invoicestat"
													name="invoicestat">
														<option value="">입/출고</option>
														<option value="Receiving">Receiving</option>
														<option value="Shipping">Shipping</option>
												</select></td>
												<td><div class="input-group">
														<input type="text" id="startdate" name="startdate"
															class="form-control mydatepicker"
															placeholder="yyyy-mm-dd"> <span
															class="input-group-append"><span
															class="input-group-text"><i
																class="mdi mdi-calendar-check"></i></span></span>
													</div></td>
												<td><div class="input-group">
														<input type="text" id="enddate" name="enddate"
															class="form-control mydatepicker"
															placeholder="yyyy-mm-dd"> <span
															class="input-group-append"><span
															class="input-group-text"><i
																class="mdi mdi-calendar-check"></i></span></span>
													</div></td>

											</tr>
											<tr>
												<td></td>
												<td></td>
												<td></td>
												<td></td>
												<td></td>

												<td><button type="submit"
														class="btn mb-1 btn-primary btn-lg" style="width: 150px;">Search</button></td>
											</tr>
										</tbody>
									</table>
								</form>

							</div>

							<div class="table-responsive">
								<table
									class="table table-striped table-bordered zero-configuration dataTable">
									<c:if test="${not empty ivTableHeader }">
										${ivTableHeader }
									</c:if>

									<tbody>
										<c:forEach var="iv" items="${invoiceList }">
											<tr>
												<td>${iv.itemid}</td>
												<td>${iv.itemname}</td>
												<td>${iv.warename}</td>
												<td>${iv.invoicestat }</td>
												<td>${iv.invoicedtlqty }</td>
												<td>${iv.invoicedtldate }</td>
											</tr>
										</c:forEach>
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



	<script>
		// warehouse id list
		var wareIdList = [ "wh1111", "#" ];

		// invoice detail item list
		var jsonInvoice = Array();

		//////////////////////////////////////////////

		function setWareID(w) {
			var target = document.getElementById("wareid_"
					+ w.value.substring(7, 8));
			if (w.value.substring(0, 6) == "ware00")
				target.value = wareIdList[0];
			else
				target.value = "";
		}

		//(2)item list에서 데이터 선택하면 (3)의 textinput에 자동으로 띄우기 
		$(function() {
			$(document.body).delegate(".selectedItList", "click", function() {
				var itemid = $(this).find("td").eq(0).text();
				var itemname = $(this).find("td").eq(1).text();
				var wareid = $(this).find("td").eq(6).text();
				var warename = $(this).find("td").eq(7).text();
				//alert($("#setItInfo").find("input[name=itemid]").val());
				$("#setItInfo").find("input[name=itemid]").val(itemid);
				$("#setItInfo").find("input[name=itemname]").val(itemname);
				$("#setItInfo").find("input[name=wareid]").val(wareid);
				$("#setItInfo").find("input[name=warename]").val(warename);
			});
		});

		// Add item to invoice
		$(function() {
			$("#addItemToInvoice").on(
					{
						click : function() {
							var jsonItem = new Object();

							jsonItem.itemid = $("#setItInfo").find(
									"input[name=itemid]").val();
							jsonItem.itemname = $("#setItInfo").find(
									"input[name=itemname]").val();
							jsonItem.wareid = $("#setItInfo").find(
									"input[name=wareid]").val();
							jsonItem.warename = $("#setItInfo").find(
									"input[name=warename]").val();
							jsonItem.invoicedtlqty = $("#setItInfo").find(
									"input[name=invoicedtlqty]").val();
							jsonItem.invoicestat = $("#setItInfo").find(
									"select[name=invoicestat]").val();

							jsonInvoice.push(jsonItem);

							updateIvDetail();

						},
						error : function(e) { // 이곳의 ajax에서 에러가 나면 얼럿창으로 에러 메시지 출력
							console.log(e.responseText);
						}
					});
		});

		// Delete item from invoice
		$(function() {
			$(document.body).delegate(".deleteIvItem", "click", function() {
				var idx = $(this).closest("tr").find("td").eq(0).text();
				//alert(idx);
				jsonInvoice.splice(idx, 1);
				//alert(JSON.stringify(jsonInvoice));

				updateIvDetail();

			});
		});

		// Register Invoice
		$(function() {
			$(document.body).delegate(
					"#ivRegister",
					"click",
					function() {
						$.ajax({
							type : "post" // 포스트방식
							,
							url : "invoiceregister.pc" // url 주소
							,
							data : JSON.stringify(jsonInvoice),
							dataType : "text",
							contentType : "application/json; charset=UTF-8",
							success : function(response) { //응답이 성공 상태 코드를 반환하면 호출되는 함수
								console.log(response);
								var token = response.responseText;
								if (token = "SUCCESS") {
									alert("발주가 완료되었습니다.");
									jsonInvoice = Array();
									$("#invoiceDetail").html("");
									updateItem();
								} else if (token = "ERROR") {
									alert("발주실패ㅒㅒㅒㅒ");
								}
							},
							error : function(request, status, error) { // 이곳의 ajax에서 에러가 나면 얼럿창으로 에러 메시지 출력
								console.log("code:" + request.status + "\n"
										+ "message:" + request.responseText
										+ "\n" + "error:" + error);
							}
						});
					});
		});

		//Item list update
		var updateItem = function() {
			$.ajax({
				type : "post" // 포스트방식
				,
				url : "itemsearch.pc" // url 주소
				,
				data : {},
				dataType : "json",
				contentType : "application/json; charset=UTF-8",
				success : function(data) { //응답이 성공 상태 코드를 반환하면 호출되는 함수
					var html = "";
				
					console.log("아이템콘솔로그"+item);

					$.each(data, function(index, item) {
						html += "<tr class=\"selectedItList\">";
						html += "<td>" + item.itemid + "</td>";
						html += "<td>" + item.itemname + "</td>";
						html += "<td>" + item.itemcate + "</td>";
						html += "<td>" + item.itemprice + "</td>";
						html += "<td>" + item.itemweightpb + "</td>";
						html += "<td>" + item.itemqtypb + "</td>";
						html += "<td>" + item.wareid + "</td>";
						html += "<td>" + item.warename + "</td>";
						html += "<td>" + item.itemloc + "</td>";
						html += "<td>" + item.itemstock + "</td>";
						html += "</tr>";
					});

					$("#itListBody").html(html);

				},
				error : function(e) { // 이곳의 ajax에서 에러가 나면 얼럿창으로 에러 메시지 출력
					console.log(e.responseText);
				}
			});
		}

		var updateIvDetail = function() {
			var html = "";

			$
					.each(
							jsonInvoice,
							function(index, jsonItemArr) {
								html += "<tr class=\"ivItemList\">";
								html += "<td>" + index + "</td>";
								html += "<td>" + jsonItemArr.itemid + "</td>";
								html += "<td>" + jsonItemArr.itemname + "</td>";
								html += "<td>" + jsonItemArr.wareid + "</td>";
								html += "<td>" + jsonItemArr.warename + "</td>";
								html += "<td>" + jsonItemArr.invoicedtlqty
										+ "</td>";
								html += "<td>" + jsonItemArr.invoicestat
										+ "</td>";
								html += "<td><span class=\"badge badge-pill badge-danger deleteIvItem\">DELETE</span></td>"
								html += "</tr>";
							});

			html += "<td></td><td></td><td></td><td></td><td></td><td></td><td><button type=\"button\" id =\"ivRegister\" class=\"btn mb-1 btn-primary btn-lg\">Order</button><td>";

			$("#invoiceDetail").html(html);
		}

		/*
		$('#showItemLoc').on('show.bs.modal', function(e) {
			console.log("2222");
			var button = $(e.relatedTarget);
			var modal = $(this);
			
			modal.find('#innerModal').load(button.data("remote"));
		
		});

		
		
				$(function() {
					$('#showItemLoc').click(function() {

					var url = "/WebApp/view/modal/itemLoc.jsp"
					$("#itemLocModal").load(url, function() { 
					        $("#itemLocModal").modal("show"); 
					 });

					});
				});
		 */

		$('#theModal').on('show.bs.modal', function(e) {

			var button = $(e.relatedTarget);
			var modal = $(this);

			// load content from HTML string
			//modal.find('.modal-body').html("Nice modal body baby...");

			// or, load content from value of data-remote url
			modal.find('.modal-body').load(button.data("remote"));

		});
		 
		  $('#theModal').on('hide.bs.modal', function () {    
		    //   console.log('username : '+$("#modal-username").val());    
		    //   console.log('result : '+$("#modal-result").val());    
		})

		$(document).ready(function() {
			updateItem();
		});
	</script>


</body>
</html>