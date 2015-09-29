<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>

<head>

	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">

	<title>J360 | 快递单查询</title>

	<link href="css/bootstrap.min.css" rel="stylesheet">
	<link href="font-awesome/css/font-awesome.css" rel="stylesheet">

	<link href="css/style.css" rel="stylesheet">
</head>

<body class="gray-bg">

<div class="middle-box text-center loginscreen animated fadeInDown">
	<div>
		<div>
			<h1 class="logo-name">J360</h1>
		</div>
		<h3>J360 | 快递单查询</h3>
		<p>请输入快递单号：
		</p>
		<form class="m-t" role="form" action="express">
			<div class="form-group">
				<input type="text" class="form-control" placeholder="快递单号" required="">
			</div>
			<button type="submit" class="btn btn-primary block full-width m-b">提交</button>

			<p class="text-muted text-center">结果</p>
			<p class="text-muted text-center"><small>价格：${express.cost}</small></p>
		</form>
		<p class="m-t"> <small>© <a href="https://github.com/xuminwlt" target="_blank">https://github.com/xuminwlt </a></small> </p>
	</div>
</div>

<!-- Mainly scripts -->
<script src="js/jquery-2.1.1.js"></script>
<script src="js/bootstrap.min.js"></script>

</body>

</html>
