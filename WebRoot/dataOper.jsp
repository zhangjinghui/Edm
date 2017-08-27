<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>DataOper</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link href="css/list.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" charset="UTF-8" src="js/jquery.min.js" ></script>
<script>
	$(function(){
		$(".nav>li").hover(function(){
			$(this).children('ul').stop(true,true).show(300);
		},function(){
			$(this).children('ul').stop(true,true).hide(300);
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
		</ul>
	</li>
	<li><a href="#">参数设置</a>
		<ul class="sub-nav">
			<li><a href="#">参数设置</a></li>
			<li><a href="#">参数设置</a></li>
			<li><a href="#">参数设置</a></li>
			<li><a href="#">参数设置</a></li>
			<li><a href="#">参数设置</a></li>
			<li><a href="#">参数设置</a></li>
		</ul>
	</li>
	<li><a href="#">交叉验证</a>
		<ul class="sub-nav">
			<li><a href="#">交叉验证</a></li>
			<li><a href="#">交叉验证</a></li>
			<li><a href="#">交叉验证</a></li>
			<li><a href="#">交叉验证</a></li>
			<li><a href="#">交叉验证</a></li>
			<li><a href="#">交叉验证</a></li>
		</ul>
	</li>
	<li><a href="#">模型训练</a>
		<ul class="sub-nav">
			<li><a href="#">模型训练</a></li>
			<li><a href="#">模型训练</a></li>
			<li><a href="#">模型训练</a></li>
			<li><a href="#">模型训练</a></li>
			<li><a href="#">模型训练</a></li>
			<li><a href="#">模型训练</a></li>
		</ul>
	</li>
	<li><a href="#">模型预测</a>
		<ul class="sub-nav">
			<li><a href="#">模型预测</a></li>
			<li><a href="#">模型预测</a></li>
			<li><a href="#">模型预测</a></li>
			<li><a href="#">模型预测</a></li>
			<li><a href="#">模型预测</a></li>
			<li><a href="#">模型预测</a></li>
		</ul>
	</li>
	<li><a href="#">应用模型</a>
		<ul class="sub-nav">
			<li><a href="#">应用模型</a></li>
			<li><a href="#">应用模型</a></li>
			<li><a href="#">应用模型</a></li>
			<li><a href="#">应用模型</a></li>
			<li><a href="#">应用模型</a></li>
			<li><a href="#">应用模型</a></li>
		</ul>
	</li>
</ul>
</div>
  </body>
</html>
