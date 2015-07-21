<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>主控机设置页面</title>
    
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/CSS/control.css">
  </head>
  
  <body>
    	<h2 class="style1">控制机管理设置</h2>
    	${jstrErrors }
    <div class="style2">
    	<form action="${pageContext.request.contextPath }/servlet/ControlSettinServlet" method="POST">
    		<table border="0" class="style3">
    			<tr><td>教师机总数：</td><td><input type="text" name="tcount" value="${control.tcount }"/></td><td><span>${control.errors.tcount }</span></td></tr>
    			<tr><td>学生机总数：</td><td><input type="text" name="scount" value="${control.scount }"/></td><td><span>${control.errors.scount }</span></td></tr>
    			<tr><td>模拟人总数：</td><td><input type="text" name="mcount" value="${control.mcount }"/></td><td><span>${control.errors.mcount }</span></td></tr>
    			<tr><td>TV总数：</td><td><input type="text" name="vcount" value="${control.vcount }"/></td><td><span>${control.errors.vcount }</span></td></tr>
    			<tr><td colspan="3"><input type="submit" value="提交"/><input  type="reset" value="重置"/></td></tr>
    		</table>
    		
    	</form>
    </div>
    	
  </body>
</html>
