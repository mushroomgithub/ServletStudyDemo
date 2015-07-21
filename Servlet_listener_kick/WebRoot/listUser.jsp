<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>My JSP 'listUser.jsp' starting page</title>
    
  </head>
  
  <body> 
  所有登录用户为：<br>
   	<c:forEach  var="entry" items="${applicationScope.map}">
   		<c:url  var="url" value="/servlet/KickServlet">
   			<c:param name="username" value="${entry.key}"></c:param>
   		</c:url>
   		${entry.key } 	<a href="${url }">踢掉</a><br>
   	</c:forEach>
  </body>
</html>
