<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>舆情信息挖掘子系统</title>
<link rel="stylesheet" href="../css/common.css" />
<link rel="stylesheet" href="../css/bootstrap.min.css" />
<script src="../js/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="../js/bootstrap.min.js"></script>
<script type="text/javascript" src="../js/bootstrap.min.js"></script>
<script type="text/javascript" src="../js/mining/dateformat.js"></script>
<script type="text/javascript" src="../js/mining/upload.js"></script>
<script type="text/javascript" src="../js/mining/show.js"></script>
<style type="text/css">
table {
	table-layout: fixed;
	word-break: break-all;
}

th, td {
	text-align: center;
	vertical-align: middle;
	overflow: hidden;
	white-space: nowrap;
}
</style>
</head>
<body>
	<div class="container">
		<div class="container">
			<div class="row clearfix">
				<div class="col-md-12 column">
					<div class="row clearfix">
						<div class="col-md-12 column">
							<h1 class="text-center">舆情信息挖掘子系统</h1>
						</div>
					</div>
					<div class="row clearfix">
						<div class="col-md-12 column">
							<div class="tabbable">
								<ul class="nav nav-tabs">
									<li class="active"><a href="#cluster" data-toggle="tab">聚类结果</a></li>
									<li><a href="#origAndCount" data-toggle="tab">原始信息及统计结果</a></li>
								</ul>
								<div class="tab-content">
									<div class="tab-pane active" id="cluster">
										<table class="table table-bordered">
											<thead>
												<tr style="font-size: 13px">
													<th style="width: 30px">属性</th>
													<th style="width: 80px">标题</th>
													<th style="width: 50px;">链接</th>
													<th style="width: 40px">来源/发布人</th>
													<th style="width: 40px">发布时间</th>
													<th style="width: 40px">网站</th>
													<th style="width: 40px">频道</th>
													<th style="width: 30px">点击数</th>
													<th style="width: 30px">回复数</th>
													<th style="width: 30px">类型</th>
													<th style="width: 30px">记者/作者</th>
													<th style="width: 30px">内容长度</th>
													<th style="width: 100px">分词</th>
													<th style="width: 100px">摘要/内容</th>
												</tr>
											</thead>
											<tbody>
												<%-- <%
													List<String[]> clusterList = (List<String[]>) request.getAttribute("clusterList");
													for (String[] row : clusterList) {
												%><tr>
													<%
														for (String cell : row) {
													%>
													<td><%=cell%></td>
													<%
														}
													%>
												</tr>
												<%
													}
												%> --%>
											</tbody>
										</table>
									</div>
									<div class="tab-pane" id="origAndCount">
										<table class="table table-bordered"
											style="table-layout: fixed;">
											<thead>
												<tr style="font-size: 13px">
													<th style="width: 30px">总计</th>
													<th style="width: 30px">属性</th>
													<th style="width: 80px">标题</th>
													<th style="width: 50px;">链接</th>
													<th style="width: 40px">来源/发布人</th>
													<th style="width: 40px">发布时间</th>
													<th style="width: 40px">网站</th>
													<th style="width: 40px">频道</th>
													<th style="width: 30px">点击数</th>
													<th style="width: 30px">回复数</th>
													<th style="width: 30px">类型</th>
													<th style="width: 30px">记者/作者</th>
													<th style="width: 30px">内容长度</th>
													<th style="width: 100px">分词</th>
													<th style="width: 100px">摘要/内容</th>
												</tr>
											</thead>
											<tbody>
												<%-- <%
													List<String[]> origAndCountList = (List<String[]>) request.getAttribute("origAndCountList");
													for (String[] row : origAndCountList) {
												%><tr>
													<%
														for (String cell : row) {
													%>
													<td><%=cell%></td>
													<%
														}
													%>
												</tr>
												<%
													}
												%> --%>
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
	</div>
</body>
</html>