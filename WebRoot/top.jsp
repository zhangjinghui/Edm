<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.edm.vo.User"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style>
body {
	background-color: #09C;
}

table {
	width: 100%;
	height: 60px;
}

.title {
	padding-left: 100px;
	font-weight: bolder;
	font-size: x-large;
	color: #fff;
	width: 90%;
}
</style>
<title>头部</title>
</head>
<body>
	<table>
		<tr>
			<td class="title">学生学习行为管理系统 &nbsp;</td>
			<td>&nbsp; <span style="color:red;font-weight: bolder;"><%=((User) session.getAttribute("user")).getUsername()%></span>
				<a href="user?action=exit" target="_top">退出</a>
			</td>
		</tr>
	</table>
</body>
</html>