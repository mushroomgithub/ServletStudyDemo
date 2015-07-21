<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>用户登录界面</title>
    
  </head>
  
  <body>
    	<form action="${pageContext.request.contextPath }/servlet/LoginServlet" method="post">
    		用户名：<input type="text" name="username"><br>
    		密&nbsp;码：<input type="password" name="password"><br>
    		有效期：
    		1分钟<input type="radio" name="time" value="${1*60 }">
    		5分钟<input type="radio" name="time" value="${5*60 }">
    		10分钟<input type="radio" name="time" value="${10*60 }"><br>
    		<input type="submit" value="登录"/>
    	</form>
  </body>
</html>
