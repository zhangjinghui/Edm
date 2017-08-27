<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*, com.edm.vo.*"%>
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
	padding-top: 60px;
}
</style>

<script type="text/javascript" src="js/jquery-1.11.1.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<script type="text/javascript">
	$(function(){
		suanfa();
	});
	function suanfa(){
		if($("#method").val()==0){
			$("#d1").html("<label>设置参数</label>"+ 
					"<div class='form-inline'>"+ 
					"<div class='form-group'>"+ 
						"<label>森林大小</label> <input class='form-control' name='ntree'"+ 
												"placeholder='默认'>"+ 
					"</div>"+ 
					"<div class='form-group'>"+ 
						"<label>决策树大小</label> <input class='form-control' name='nodesize'"+ 
												"	placeholder='默认'>"+ 
					"</div>"+ 
					"<div class='form-group'>"+ 
						"<label>随机种子</label> <input class='form-control' name='seed'"+ 
										"		placeholder='随机'>"+ 
				"	</div>"+ 
				"</div>");
		}else if($("#method").val()==1){
			$("#d1").html("<label>设置参数</label>"+ 
					"<div class='form-inline'>"+ 
					"<div class='form-group'>"+ 
						"<label>degree</label> <input class='form-control' name='degree'"+ 
												"placeholder='默认'>"+ 
					"</div>"+ 
					"<div class='form-group'>"+ 
						"<label>knots</label> <input class='form-control' name='knots'"+ 
												"	placeholder='默认'>"+ 
					"</div>"+  
				"</div>");
		}else if($("#method").val()==3){
			$("#d1").html("<label>设置参数</label>"+ 
					"<div class='form-inline'>"+ 
					"<div class='form-group'>"+ 
						"<label>k</label> <input class='form-control' name='k'"+ 
												"placeholder='默认'>"+ 
				"</div>");
		}else{
			$("#d1").html("");
		}
	}
</script>
</head>
<body>
	<%
		List<Files> list = (List<Files>) request.getAttribute("list");
		Files file = null;
	%>
	<div class="container">

		<form action="data" method="post">
			<div class="form-group">
				<label>选择文件</label> <select class="form-control" name="fileName">
					<%
						for (int i = 0; i < list.size(); i++) {
																									file = (Files) list.get(i);
					%>
					<option value=<%=file.getFileName()%>><%=file.getFileName()%></option>
					<input type="hidden" name="id" value="<%=file.getId()%>" />
					<%
						}
					%>
				</select>
			</div>
			<div class="form-group">
				<label>选择算法</label> <select name="method" id="method"
					class="form-control" onchange="suanfa();">
					<option value="0">随机森林</option>
					<option value="1">支持向量机</option>
					<option value="2">决策树</option>
					<option value="3">K最近邻</option>
					<option value="4">多元自适应回归样条</option>
				</select>
			</div>

					<div class="form-group" id="d1">
							
					</div>
					
			<button class="btn btn-info" type="submit">生成数据</button>
		</form>
	</div> 
	<script type="text/javascript">
		function checkMode() {
			document.getElementsByName('method')[0]
					.getElementsByTagName('option')[
	<%=(String)request.getAttribute("index")%>
		].selected = true;
		}
		checkMode();
		
	</script>
	<%
		System.out.println((String) request.getAttribute("index"));
	%>
</body>
</html>