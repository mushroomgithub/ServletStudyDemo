<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!-- 首页添加网站功能，这里使用分帧的方式将首页分为上下两帧-->
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>首页</title>
  </head>
  
  <frameset rows="20%,*">
  	<frame name="head" src="${pageContext.request.contextPath }/head.jsp">
  	<frame name="main" src="#">
  </frameset>
  
</html>
