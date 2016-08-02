<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>文件上传测试</title>
<link rel="stylesheet" href="../css/common.css" />
<link rel="stylesheet" href="../css/bootstrap.min.css" />
<script src="../js/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="../js/bootstrap.min.js"></script>
<script type="text/javascript" src="../js/bootstrap.min.js"></script>
<script type="text/javascript" src="../js/mining/dateformat.js"></script>
<script type="text/javascript" src="../js/mining/upload.js"></script>
</head>
<body>
	<div class="container">
		<div class="row clearfix">
			<div class="col-md-12 column">
				<table class="table">
					<tbody>
						<%
						    List<String[]> list = (List<String[]>)request.getAttribute("list");
						    for (String[] row : list) {
						%><tr>
							<td style="text-align: center; vertical-align: middle;">
								<%= row[0] %>
							</td>
							<td style="text-align: center; vertical-align: middle;">
								<%= row[1] %>
							</td>
							<td style="text-align: center; vertical-align: middle;">
								<%= row[2] %>
							</td>
							<td style="text-align: center; vertical-align: middle;">
								<%= row[3] %>
							</td>
							<td style="text-align: center; vertical-align: middle;">
								<%= row[4] %>
							</td>
							<td style="text-align: center; vertical-align: middle;">
								<%= row[5] %>
							</td>
							<td style="text-align: center; vertical-align: middle;">
								<%= row[6] %>
							</td>
							<td style="text-align: center; vertical-align: middle;">
								<%= row[7] %>
							</td>
							<td style="text-align: center; vertical-align: middle;">
								<%= row[8] %>
							</td>
							<td style="text-align: center; vertical-align: middle;">
								<%= row[9] %>
							</td>
							<td style="text-align: center; vertical-align: middle;">
								<%= row[10] %>
							</td>
							<td style="text-align: center; vertical-align: middle;">
								<%= row[11] %>
							</td>
						</tr>
						<%
						    }
						%>
					</tbody>
				</table>
			</div>
		</div>
		<div class="row clearfix">
			<div class="col-md-12 column"></div>
		</div>
	</div>
</body>
</html>