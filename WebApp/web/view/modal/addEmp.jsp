<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="table-responsive">
		<table class="table">
			<thead></thead>
			<tbody>
				<tr>
					<td><input type="text" class="form-control" id="empno"
						name="empno" placeholder="ID"></td>
					<td><input type="text" class="form-control" id="empname"
						name="empname" placeholder="Name"></td>
						
					<td><input type="password" class="form-control" id="emppw"
						name="emppw" placeholder="Pwd"></td>
					<td><select class="form-control" id="empjob" name="empjob">
							<option value="">Position</option>
							<option value="관리자">관리자</option>
							<option value="일반">일반</option>

					</select></td>
						
				</tr>
				<tr>
				<td><input type="email" class="form-control" id="empemail"
						name="empemail" placeholder="Email"></td>
					<td><input type="tel" class="form-control"
						id="empphone" name="empphone" placeholder="Phone"></td>
					
					<td><div class="whInfo">
							<input type="text" class="form-control" id="wareid_R"
								name="wareid" placeholder="창고ID" readonly> <select
								class="form-control" id="warename" name="warename"
								style="width: 150px;" onchange="setWareID(this)">
								<option>창고명</option>
								<option value="ware00_R">이천 제1물류창고</option>
								<!--  <option value="ware01">CSS</option>-->
							</select>
						</div></td>
					<td></td>
				</tr>
			</tbody>
		</table>
	</div>



	<script>
		
	</script>
</body>
</html>