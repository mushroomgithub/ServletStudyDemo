<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>My JSP 'index.jsp' starting page</title>
    
  </head>
  
  <body>
  	<%-- 
  		<%
  			System.out.println("index.jsp");
  	 	%>
  	 --%>
    Hello,Mushroom <br>
    欢迎你：${user.username }
    <img src="${pageContext.request.contextPath }/pic/dog.bmp" width="40px" height="40px"><br>
    <!-- 这里为了测试使用过滤器真正解决了全站乱码问题，将提交方式设置为get方法 -->
    <form action="${pageContext.request.contextPath }/servlet/ServletDemo1" method="get">
    	用户名：<input type="text" name="username"><br>
    	密码：<input type="password" name="password"><br>
    	<textarea rows="5" cols="100" name="resume"></textarea>
    	<input type="submit" value="登录">
    </form>
  </body>
</html>
