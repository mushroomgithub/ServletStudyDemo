<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>下载文件列表</title>
    
  </head>
  
  <body>
  		<h1>下载文件有：</h1>
   		<c:forEach var="entry" items="${map }">
   			<!-- 使用jstl url标签帮助我们生成url地址，防止url后面带的参数中存在中文乱码问题 value是要生成的超链接地址，不用再跟上pageContext...，jstl标签会自动为其加上 ，最后把重构的url地址存在page域中的var值中了-->
   			<c:url var="url" value="/servlet/DownloadServlet">
   				<c:param name="filename" value="${entry.key }"></c:param><!-- 为这个生成的超链接加上参数,这个参数会自动编码中文，最后得到的参数都是经过编码之后的参数，不会出现中文乱码问题 -->
   			</c:url>
   			<!--  
   			${entry.value } <a href="${pageContext.request.contextPath }/servlet/DownloadServlet?filename='${map.key }'">下载</a><br/>
   			-->
   			${entry.value } <a href="${url }">下载</a><br/>
   		</c:forEach>
  </body>
</html>
