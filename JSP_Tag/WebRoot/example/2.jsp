<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/example" prefix="example" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>开发if标签</title>
    
  </head>
  
  <body> 
    <%
    	session.setAttribute("user","mushroom");
     %>
    <example:if test="${user==null}">
    	你还没有登陆，请先登录！<br/>
    </example:if>
    
    <example:if test="${user!=null}">
    	欢迎你！${user }<br/>
    </example:if>
  </body>
</html>
