<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>首页</title>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  
  <body style="text-align:center;color:red;">
  	<h2>企业信息管理网站</h2>
  	<!-- 如果用户已经登陆，给个欢迎你：用户名，否则给用户提功一个注册和登陆按钮 -->
  	<!-- 在四个域中查找user属性，如果查找到说明用户登陆成功，要使用这种方法需要使用taglib标签，注意在standard-1.1.2.jar/META-INF/c.tld找uri名，前缀设置为c.tld的名即c -->
  	<div style="text-align:right; color:green;">
  		<c:if test="${user!=null}">
  			欢迎你：${user.nickname } <a href="${pageContext.request.contextPath }/servlet/LogoutServlet">注销</a>
  		</c:if>
  	
  		<c:if test="${user==null}">
    		 <a href="${pageContext.request.contextPath }/servlet/RegisterUIServlet">注册</a>
     		<a href="${pageContext.request.contextPath }/servlet/LoginUIServlet">登陆</a>
     	</c:if>
   </div>
   <hr>
  </body>
</html>
