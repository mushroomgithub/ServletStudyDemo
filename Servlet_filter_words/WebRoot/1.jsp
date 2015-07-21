<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>My JSP '1.jsp' starting page</title>
    
  </head>
  
  <body>
   <form action="${pageContext.request.contextPath }/servlet/ServletDemo1" method="post">
    	<textarea rows="5" cols="100" name="resume"></textarea>
    	<input type="submit" value="登录">
    </form>
  </body>
</html>
