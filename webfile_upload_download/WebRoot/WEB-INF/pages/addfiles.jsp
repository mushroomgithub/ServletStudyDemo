<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>上传页面</title>
    
  </head>
 			
  <body style="text-align:center;">
  <form action="${pageContext.request.contextPath }/servlet/UpfileServlet" enctype="multipart/form-data" method="post">
    <table width="50%" frame="border">
    	<tr>
    		<td>上传用户:</td>
    		<td>
    			<input type="text" name="username" />
    		</td>
    	</tr>
    	
    	<tr>
    		<td>上传文件:</td>
    		<td>
    			<input type="file" name="file" />
    		</td>
    	</tr>
    	
    	<tr>
    		<td>文件描述:</td>
    		<td>
    			<textarea rows="5" cols="50" name="description"></textarea>
    		</td>
    	</tr>
    	<tr>
    		<td colspan="2">
    			<input type="submit" value="上传" />
    		</td>
    	</tr>
    </table>
   </form>
  </body>
</html>
