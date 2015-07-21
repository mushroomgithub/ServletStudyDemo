<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head> 
    <title>首页</title>
  </head>
  
  <!-- 首页使用分帧技术，注意，分帧不能写在body体内 -->
  <frameset rows="20%,*">
  	<frame name="head" src="${pageContext.request.contextPath}/head.jsp">
	<frame name="main" src="#">
  </frameset>
</html>
