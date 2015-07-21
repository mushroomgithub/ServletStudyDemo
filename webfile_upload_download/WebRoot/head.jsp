<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>页头</title>
    
  </head>
  
  <body style="text-align:center;">
   	<h1>文件上传与下载页面</h1>
   <a href="${pageContext.request.contextPath}/servlet/UpfileServlet" target="main">上传文件</a>
   <a href="${pageContext.request.contextPath}/servlet/ListFileServlet" target="main">下载文件</a>
  </body>
</html>
