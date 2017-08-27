<%@ page language="java" import="com.zhuozhengsoft.pageoffice.*"%>
<%@ taglib uri="http://java.pageoffice.cn" prefix="po"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		String path = (String) request.getAttribute("path");

		PageOfficeCtrl poCtrl1 = new PageOfficeCtrl(request);
		poCtrl1.setServerPage("poserver.zz"); //此行必须
		poCtrl1.setSaveFilePage("savefile.jsp");//如要保存文件，此行必须
		poCtrl1.addCustomToolButton("保存", "Save()", 1);//添加自定义工具栏按钮
		//打开文件
		poCtrl1.webOpen(path, OpenModeType.xlsNormalEdit, "张三");
		poCtrl1.setTagId("PageOfficeCtrl1"); //此行必须
	%>
	<script type="text/javascript">
		function Save() {
			document.getElementById("PageOfficeCtrl1").WebSave();
		}
	</script>
	<div>
		<div style="height:580px;">
			<po:PageOfficeCtrl id="PageOfficeCtrl1" />
		</div>
	</div>
</body>
</html>