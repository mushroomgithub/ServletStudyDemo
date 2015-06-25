<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>JSP 常用标签2</title>
    
  </head>
  
  <body>
   	<jsp:forward page="/servlet/ServletDemo">
   		<jsp:param name="username" value="mushroom"/>
   		<jsp:param name="password" value="123456"/>
   	</jsp:forward>
  </body>
</html>
