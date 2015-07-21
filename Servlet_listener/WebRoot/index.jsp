<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="cn.lnu.domain.User" %>
<%@ page import="cn.lnu.domain.myBean" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  
  <body>
  	当前在线人数：${applicationScope.count }人<br>
  	
  	<%
  		//application.setAttribute("name","mushroom");
  		//application.setAttribute("name","mogu");
  		//application.removeAttribute("name");
  		
  		//session.setAttribute("name","aaa");
  		//session.setAttribute("name","bbb");
  		//session.removeAttribute("name");
  		
  		//request.setAttribute("name","aaa");
  		//request.setAttribute("name","bbb");
  		//request.removeAttribute("name");
  		
  		//session.setAttribute("user",new User());
  		//session.removeAttribute("user");
  		
  		session.setAttribute("bean",new myBean());
  	 %>
  </body>
</html>
