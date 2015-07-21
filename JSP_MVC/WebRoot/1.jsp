<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>jsp：useBean标签的使用</title>
    	
  </head>
  
  <body>
  	<!-- jsp:useBean标签之间的内容只在实例bean对象时执行 -->
   	<jsp:useBean id="person" class="cn.lnu.domain.Person" scope="page"></jsp:useBean>
   	<%
   		person.setName("haha");
   	 %>
   	<%=person.getName() %>
  </body>
</html>
