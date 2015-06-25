<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>Include指令{静态包含，(编译时包含)它包含的所有jsp会编译一个servlet}</title>
    
  </head>
  
  <body>
  	<%@include file="/public/head.jsp"%>
  	<font color="red" size="40px">
    设置页头和页脚<br/>
    </font>
    
    <%@include file="/public/foot.jsp"%>
    
  </body>
</html>
