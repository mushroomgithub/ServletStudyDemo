<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>用户注册页面</title>
    
  </head>
  
  <body>
    	<form action="${pageContext.request.contextPath }/servlet/RegisterServlet" method="post">
    		用户名:<input type="text" name="username"><br>
    		密码:<input type="password" name="password"><br>
    		电子邮箱:<input type="text" name="email"><br>
    		<input type="submit" value="注册"><br>
    	</form>
  </body>
</html>
