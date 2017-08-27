<%@ page language="java"
	import="java.util.*,com.zhuozhengsoft.pageoffice.*,com.edm.vo.*"
	pageEncoding="gb2312"%>
<%
	FileSaver fs = new FileSaver(request, response);
	fs.saveToFile(request.getSession().getServletContext()
			.getRealPath("\\")
			+ "\\" + fs.getFileName());
	fs.close();
%>