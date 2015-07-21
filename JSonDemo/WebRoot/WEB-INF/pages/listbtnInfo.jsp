<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>显示客户端按键操作信息页面</title>
    
  </head>
  
  <body style="text-align:center;">
   	<table width="50%" frame="border">
   		<tr>
   			<td>操作码</td>
   			<td>按键名称</td>
   			<td>按钮背景图片id</td>
   		</tr>
   		
   		<tr>
   			<td>
   				<input type="text" name="id" value="${bean.id }" />
   			</td>
   			<td>
   				<input type="text" name="id" value="${bean.btnname }" />
   			</td>
   			<td>
   				<input type="text" name="id" value="${bean.btnid }" />
   			</td>
   		</tr>
   		
   	</table>
  </body>
</html>
