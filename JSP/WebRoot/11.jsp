<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>JSP常用标签</title>
    
  </head>
  
  <body>
   	
   	<jsp:forward page="/index.jsp"></jsp:forward>
   
   <jsp:include page="/public/head.jsp"></jsp:include>  <!-- 相当于pageContext.include（） 动态包含，一般用的不多 -->
   
   
  </body>
</html>
