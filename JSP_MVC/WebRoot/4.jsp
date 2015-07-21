<%@ page language="java" import="java.util.*,cn.lnu.domain.*" pageEncoding="UTF-8"%>
<%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>使用jstl+el完成集合迭代</title>
    
  </head>
  
  <body>
     <%
    	   	List list=new ArrayList();
    	   	list.add(new Person("dada"));
    	   	list.add(new Person("fafa"));
    	   	list.add(new Person("gaga"));
    	   	
    	   	request.setAttribute("list",list);
    %>
    	<c:forEach var="person" items="${list}">
    	${person.name }<br/>
    	</c:forEach>  
    	
    	
    	 <%
    	   	Map map=new HashMap();
    	   	map.put("aa",new Person("aaaaaa"));
    	   	map.put("bb",new Person("bbbbbb"));
    	   	map.put("cc",new Person("cccccc"));
    	   	map.put("dd",new Person("dddddd"));
    	   	
    	   	request.setAttribute("map",map);
    	    %>
    	    <c:forEach var="entry" items="${map}">
    	    	${entry.key }:${entry.value.name} <br/>
    	    </c:forEach>
    	    
    	    <!-- 测试某一条件是否成立 -->
    	    	<!-- 表示用户已经登陆 -->
    	    <c:if test="${user!==null}">
    	    		欢饮你： ${user.username }
    	    </c:if>
    	    	<!-- 表示用户hia没有登陆，提示其登陆 -->
    	    <c:if test="${user==null}">
    	    	<h1>登陆表单</h1><br/>
    	    	用户名： <input type="text" name="username"/><br/>
    	    	密码：<input type="password" name="password" /><br/>
    	    </c:if>
    	    
  </body>
</html>
