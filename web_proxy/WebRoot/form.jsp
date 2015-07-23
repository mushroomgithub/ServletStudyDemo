<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>My JSP 'form.jsp' starting page</title>
    
  </head>
  
  <body>
    <form action="${pageContext.request.contextPath }/servlet/ServletDemo1" method="get">
    	<input type="text" name="username" /><br>
    	<input type="submit" value="提交"/>
    </form>
  </body>
</html>
