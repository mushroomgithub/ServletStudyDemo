<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="org.apache.struts.action.ActionErrors"%>
<%@page import="org.apache.struts.action.ActionMessage"%>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>My JSP 'register.jsp' starting page</title>
    
  </head>
  
  <body>
  
  <% 
  /*ActionErrors errors=(ActionErrors)pageContext.findAttribute("org.apache.struts.action.ERROR"); 
  	if(errors!=null){ 
  		Iterator it=errors.get("username"); 
  		if(it.hasNext()){ 
  			ActionMessage message=(ActionMessage)it.next(); 
  			String info=message.getKey(); 
  			out.print(info); 
  		}
  	}*/
  %>
  
   <!-- 下面这句使用标签获得struts关于username属性错误信息内容 -->
   <br>传统的form表单标签的数据回显功能和错误提示 :<br>
  
    <form action="${pageContext.request.contextPath }/register.do" method="post">
    	用户名：<input type="text" name="username" value="${registerForm.username }"><html:errors property="username"/><br>
    	密&nbsp;码:<input type="password" name="password" value="${registerForm.password }"><html:errors property="password"/><br>
    	邮&nbsp;箱：<input type="text" name="email"><br>
    	<input type="submit" value="注册">
    </form>
    
    <br>struts的form表单标签天生具有数据回显功能 :<br>
    <html:form action="/register.do" method="post" enctype="multipart/form-data">
    	用户名：<html:text property="username"/><html:errors property="username"/><br>
    	密&nbsp;码：<html:password property="password"/><html:errors property="password"/><br>
    	邮&nbsp;箱：<html:text property="email"/><br>
    	大头照：<input type="file" name="file"><br>
    	<input type="submit" value="注册">
    </html:form>
    
    <br>
    
     <form action="${pageContext.request.contextPath }/register2.do" method="post">
    	用户名：<input type="text" name="username" ><br>
    	密&nbsp;码:<input type="password" name="password" ><br>
    	邮&nbsp;箱：<input type="text" name="email"><br>
    	<input type="submit" value="注册">
    </form>
    <br>
    
  </body>
</html>
