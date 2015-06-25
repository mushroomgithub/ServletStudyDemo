<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>动态包含页头页脚{是运行时包含，实际开发中还是尽量使用静态包含的方式}</title>
    
  </head>
  
  <body>
  	<%
  		request.getRequestDispatcher("/public/head.jsp").include(request,response);
  	 %>
  	 
  	<font color="red" size="40px">
  		<% 
  			response.getWriter().write(" 设置页头和页脚<br/>");
    	%>
    </font>
    
    <%
  		request.getRequestDispatcher("/public/foot.jsp").include(request,response);
  	 %>
  </body>
</html>
