<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/simpletag" prefix="Simplelnu"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>开发带属性的标签</title>
    
  </head>
  
  <body>
   		<Simplelnu:demo5 count="9" date="<%=new Date() %>">
   			Hello,world!<br/>
   		</Simplelnu:demo5>
  </body>
</html>
