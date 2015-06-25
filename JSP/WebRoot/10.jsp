<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>pageContext对象的其他常用方法</title>
    
  </head>
  
  <body>
    <%
    	pageContext.forward("/i.jsp");
    	pageContext.include("/public/head.jsp");
     %>
  </body>
</html>
