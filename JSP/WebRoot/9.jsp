<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>pageContext对象访问其他域</title>
    
  </head>
  
  <body>
  	<%
  		request.setAttribute("data","mushroom");
  		//通过page域访问request域对象中的数据
  		String data=(String)pageContext.getAttribute("data",PageContext.REQUEST_SCOPE);
  		out.write(data+"<br/>");
  		
  		//在四个域中查找属性名为data的对象，最后没找到，返回空，el表达式的内部实现就是调用了该函数(重点)
  		pageContext.findAttribute("data");//查找过程：page（pageContext）-->request-->session-->application（servletContext）
  	 %>
  </body>
</html>
