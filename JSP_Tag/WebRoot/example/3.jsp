<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/example" prefix="example"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>开发if-else标签</title>
    
  </head>
  
  <body>
  <%
  	session.setAttribute("user","mogu");
   %>
  <!-- 要想实现实现一个标签执行，另一个标签不执行，这两个标签的处理器类必须共享一个变量，方式是共享一个父亲 -->
  <example:choose>
   		 <example:when test="${user==null}">
    		你还没有登陆，请先登录！<br/>
    		</example:when>
    
   		<example:otherwise>
    	   欢迎你！${user }<br/>
    		</example:otherwise>
    </example:choose>
  </body>
</html>
