<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*, com.edm.vo.*"%>
<%
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+"/"+request.getContextPath()+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>以后文件</title>
<base href="basePath">
<link href="css/bootstrap.min.css" rel="stylesheet" />
<link href="css/list.css" rel="stylesheet" type="text/css" />

<script src="<%=basePath%>js/jquery.min.js"></script>
<script src="<%=basePath%>js/bootstrap.min.js"></script>
<script type="text/javascript" charset="UTF-8" src="js/jquery.min.js"></script>
<script>
	$(function() {
		$(".nav>li").hover(function() {
			$(this).children('ul').stop(true, true).show(300);
		}, function() {
			$(this).children('ul').stop(true, true).hide(300);
		})
	})
</script>
</head>
<body>
	<div class="menu">
		<ul class="nav">
			<li><a href="#">导入数据</a>
				<ul class="sub-nav">
					<li><a href="#">导入数据</a></li>
					<li><a href="#">导入数据</a></li>
					<li><a href="#">导入数据</a></li>
					<li><a href="#">导入数据</a></li>
					<li><a href="#">导入数据</a></li>
					<li><a href="#">导入数据</a></li>
				</ul></li>
			<li><a href="#">参数设置</a>
				<ul class="sub-nav">
					<li><a href="#">参数设置</a></li>
					<li><a href="#">参数设置</a></li>
					<li><a href="#">参数设置</a></li>
					<li><a href="#">参数设置</a></li>
					<li><a href="#">参数设置</a></li>
					<li><a href="#">参数设置</a></li>
				</ul></li>
			<li><a href="#">交叉验证</a>
				<ul class="sub-nav">
					<li><a href="#">交叉验证</a></li>
					<li><a href="#">交叉验证</a></li>
					<li><a href="#">交叉验证</a></li>
					<li><a href="#">交叉验证</a></li>
					<li><a href="#">交叉验证</a></li>
					<li><a href="#">交叉验证</a></li>
				</ul></li>
			<li><a href="#">模型训练</a>
				<ul class="sub-nav">
					<li><a href="#">模型训练</a></li>
					<li><a href="#">模型训练</a></li>
					<li><a href="#">模型训练</a></li>
					<li><a href="#">模型训练</a></li>
					<li><a href="#">模型训练</a></li>
					<li><a href="#">模型训练</a></li>
				</ul></li>
			<li><a href="#">模型预测</a>
				<ul class="sub-nav">
					<li><a href="#">模型预测</a></li>
					<li><a href="#">模型预测</a></li>
					<li><a href="#">模型预测</a></li>
					<li><a href="#">模型预测</a></li>
					<li><a href="#">模型预测</a></li>
					<li><a href="#">模型预测</a></li>
				</ul></li>
			<li><a href="#">应用模型</a>
				<ul class="sub-nav">
					<li><a href="#">应用模型</a></li>
					<li><a href="#">应用模型</a></li>
					<li><a href="#">应用模型</a></li>
					<li><a href="#">应用模型</a></li>
					<li><a href="#">应用模型</a></li>
					<li><a href="#">应用模型</a></li>
				</ul></li>
		</ul>
	</div>
	<%
		List<Files> list = (List<Files>) request.getAttribute("list");
		Files file = null;
	%>
	<div class="container">
		<table class="table table-bordered table-striped table-hover">
			<thead>
				<tr class="active danger">
					<td>文件名</td>
					<td>上传时间</td>
					<td>分析结果</td>
					<td>操作</td>
				</tr>
			</thead>
			<%
				for (int i = 0; i < list.size(); i++) {
					file = (Files) list.get(i);
			%>
			<tr>
				<td><%=file.getFileName()%></td>
				<td><%=file.getDate()%></td>
				<td><%=file.getAnalysisResult()%></td>
				<td>
					<a href="file?action=delete&id=<%=file.getId()%>">删除</a>&nbsp;&nbsp;
					<a href="file?action=edit&filename=<%=file.getFileName()%>" target="_blank">编辑</a>&nbsp;&nbsp;
					<a href="DataAnalysis/<%=((User)request.getSession().getAttribute("user")).getUsername() %>/<%=file.getFileName()%>" target="_blank">导出</a>
				</td>
			</tr>
			<%
				}
			%>
		</table>
	</div>
</body>
</html>