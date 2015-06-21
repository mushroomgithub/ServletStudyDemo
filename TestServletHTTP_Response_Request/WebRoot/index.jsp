<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  </head>
  
  <body>
	<a href="/TestServletHTTP_Response_Request/servlet/ResponseDemo6">查看图书</a><br/>
	<span  style="font-size:100px;color='red'">广告内容</span><br/>
	<a href="/TestServletHTTP_Response_Request/servlet/RequestDemo9">小蘑菇学习Servlet日记</a>
  </body>
</html>
