<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>错误页面</title>
<link rel="stylesheet" href="../css/common.css" />
<link rel="stylesheet" href="../css/bootstrap.min.css" />
<script src="../js/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="../js/bootstrap.min.js"></script>
<script type="text/javascript" src="../js/bootstrap.min.js"></script>
<script type="text/javascript" src="../js/mining/dateformat.js"></script>
<script type="text/javascript" src="../js/mining/upload.js"></script>
</head>
<body>
	出错啦！
	<%= (String)request.getAttribute("msg") %>
</body>
</html>