<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>列出所有文件</title>
    
  </head>
  
  <body style="text-align:center;">
    	<table frame="border">
    		<tr>
    			<td>文件名称</td>
    			<td>上传时间</td>
    			<td>文件描述</td>
    			<td>上传人</td>
    			<td>操作</td>
    		</tr>
    	<c:forEach var="upfile" items="${list }">
    		<tr>
    			<td>${upfile.realname }</td>
    			<td>${upfile.uptime }</td>
    			<td>${upfile.description }</td>
    			<td>${upfile.username }</td>
    			<td>
    				<c:url var="url" value="/servlet/DownloadFileServlet">
    					<c:param name="id" value="${upfile.id}"></c:param>
    				</c:url>
    				<a href="${url }">下载</a>
    				<a href="#">修改文件信息</a>
    				<a href="#">删除</a>
    			</td>
    		</tr>
    	</c:forEach>	
    		
    	</table>
  </body>
</html>
