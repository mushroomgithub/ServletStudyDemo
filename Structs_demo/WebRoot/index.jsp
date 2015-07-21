<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
  </head>
  
  <body>
    <a href="${pageContext.request.contextPath }/registerUI.do">注册</a><br>
    <!-- 采用structs link标签方法创建超链接，action属性后面也不用加.do，也可以自动对url重写，也可以给url带上参数 -->
    <%--模拟给超链接带一个参数 --%>
    <%
    	//request.setAttribute("name","中国");//带中文的话，会自动给其编码
    	request.setAttribute("name","mushroom");
     %>
     <%--模拟给超链接带多个参数 --%>
     <%
     	Map map=new HashMap();
     	map.put("name","mushroom");
     	map.put("password","123");
     	request.setAttribute("map",map);
      %>
    <html:link action="/registerUI" paramId="name" paramName="name">注册</html:link><br>
    <html:link action="/registerUI" name="map">注册</html:link>
   
   <br><br>
   <br>-----------第一种四个超链接找一个action-------<br>
   <html:link action="/bookAction?method=add">增加书籍</html:link>
   <html:link action="/bookAction?method=update">修改书籍</html:link>
   <html:link action="/bookAction?method=find">查找书籍</html:link>
   <html:link action="/bookAction?method=delete">删除书籍</html:link>
   
    <br><br>
   <br>-----------第二种四个超链接找一个action(更直观)-------<br>
   <html:link action="/addBook">增加书籍</html:link>
   <html:link action="/updateBook">修改书籍</html:link>
   <html:link action="/findBook">查找书籍</html:link>
   <html:link action="/deleteBook">删除书籍</html:link>
  </body>
</html>
