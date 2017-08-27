<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*,com.edm.vo.User"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ "/" + request.getContextPath() + "/";
%>
<base href="basePath">
<link href="css/bootstrap.min.css" rel="stylesheet" />
<style>
body {
	padding-top: 30px;
}

span {
	font-family: '雅黑';
	font-size: x-large;
	font-weight: bolder;
}
</style>
</head>
<body>
	<%
		List titleList = (List) request.getAttribute("titleList");
		List sumInfoList = (List) request.getAttribute("sumInfoList");
		double accuracy = (Double) request.getAttribute("accuracy");
	%>
	<div class="container-fluid">
		<span>原始数据概要分析</span>
		<hr />
		<table class="table table-striped table-hover">
			<%
				for (int i = 0; i < titleList.size() - 1; i++) {
			%>
			<tr>
				<td><%=titleList.get(i)%></td>

				<td><%=sumInfoList.get(i * 6)%></td>
				<td><%=sumInfoList.get(i * 6 + 1)%></td>
				<td><%=sumInfoList.get(i * 6 + 2)%></td>
				<td><%=sumInfoList.get(i * 6 + 3)%></td>
				<td><%=sumInfoList.get(i * 6 + 4)%></td>
				<td><%=sumInfoList.get(i * 6 + 5)%></td>
			</tr>
			<%
				}
			%>
		</table>
		<br /> <span>MARS分析结果</span>
		<hr />
		<table class="table table-striped table-hover">
			<tr>
				<td>算法预测准确性</td>
				<td><%=Math.round(accuracy * 100)%>%</td>
			</tr>
		</table>
	</div>
</body>
</html>