<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/example" prefix="example"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>开发foreach标签</title>
    
  </head>
  
  <body>
  <!-- 模拟充服务器端的servlet带来的数据存在request域中的list属性中 -->
    <%
    	List list=new ArrayList();
    	list.add("aaaa");
    	list.add("bbbb");
    	list.add("cccc");
    	list.add("dddd");
    	request.setAttribute("list",list);
     %>
     <!-- var相当于是一个域关键字属性 -->
     <example:foreach items="${list}" var="str">
     	${str }
     </example:foreach>
  </body>
</html>
