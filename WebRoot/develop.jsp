<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>以后文件</title>
<link href="css/bootstrap.min.css" rel="stylesheet" />
<style>
.mainWin {
	margin-top: 100px;
	text-align: center;
}
</style>
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<link href="css/list.css" rel="stylesheet" type="text/css" />
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
					<li><a href="#">导入数据</a>
					</li>
					<li><a href="#">导入数据</a>
					</li>
					<li><a href="#">导入数据</a>
					</li>
					<li><a href="#">导入数据</a>
					</li>
					<li><a href="#">导入数据</a>
					</li>
					<li><a href="#">导入数据</a>
					</li>
				</ul></li>
			<li><a href="#">参数设置</a>
				<ul class="sub-nav">
					<li><a href="#">参数设置</a>
					</li>
					<li><a href="#">参数设置</a>
					</li>
					<li><a href="#">参数设置</a>
					</li>
					<li><a href="#">参数设置</a>
					</li>
					<li><a href="#">参数设置</a>
					</li>
					<li><a href="#">参数设置</a>
					</li>
				</ul></li>
			<li><a href="#">交叉验证</a>
				<ul class="sub-nav">
					<li><a href="#">交叉验证</a>
					</li>
					<li><a href="#">交叉验证</a>
					</li>
					<li><a href="#">交叉验证</a>
					</li>
					<li><a href="#">交叉验证</a>
					</li>
					<li><a href="#">交叉验证</a>
					</li>
					<li><a href="#">交叉验证</a>
					</li>
				</ul></li>
			<li><a href="#">模型训练</a>
				<ul class="sub-nav">
					<li><a href="#">模型训练</a>
					</li>
					<li><a href="#">模型训练</a>
					</li>
					<li><a href="#">模型训练</a>
					</li>
					<li><a href="#">模型训练</a>
					</li>
					<li><a href="#">模型训练</a>
					</li>
					<li><a href="#">模型训练</a>
					</li>
				</ul></li>
			<li><a href="#">模型预测</a>
				<ul class="sub-nav">
					<li><a href="#">模型预测</a>
					</li>
					<li><a href="#">模型预测</a>
					</li>
					<li><a href="#">模型预测</a>
					</li>
					<li><a href="#">模型预测</a>
					</li>
					<li><a href="#">模型预测</a>
					</li>
					<li><a href="#">模型预测</a>
					</li>
				</ul></li>
			<li><a href="#">应用模型</a>
				<ul class="sub-nav">
					<li><a href="#">应用模型</a>
					</li>
					<li><a href="#">应用模型</a>
					</li>
					<li><a href="#">应用模型</a>
					</li>
					<li><a href="#">应用模型</a>
					</li>
					<li><a href="#">应用模型</a>
					</li>
					<li><a href="#">应用模型</a>
					</li>
				</ul></li>
		</ul>
	</div>
	<div class="container">
		<div class="centermiddle">
			<img src="images/dev.jpg">
		</div>
	</div>
</body>
</html>