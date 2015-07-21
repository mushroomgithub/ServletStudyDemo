<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>jsp:setProperty标签</title>
    
  </head>
  
  <body>
   	<jsp:useBean id="person" class="cn.lnu.domain.Person" scope="page"></jsp:useBean>
   	<!-- 手工为bean属性赋值 -->
   	<jsp:setProperty name="person" property="age" value="10" />
   	<%=person.getAge() %><br/>
   	
   	<!-- 用请求参数给bean的属性赋值 (在浏览器中访问2.jsp时，通过请求参数 ？name=xxxxx 可以给bean对象的name属性赋值为xxxx)-->
   	<jsp:setProperty name="person" property="name" param="name" /><br/>
   	<%=person.getName() %><br/>
   	<!-- 使用请求参数给bean对象传值时，支持8种基本数据类型的转换 ，即把客户机浏览器提交过来的字符串，转成相应的8种基本类型，赋值到bean的属性值中-->
   	<jsp:setProperty name="person" property="age" param="age" /><br/>
   	<%=person.getAge() %><br/>
   	<!-- 对于非8种基本类型可以使用value为bean赋值 -->
   	<jsp:setProperty name="person" property="birthday" value="<%=new Date() %>"/>
   	<%=person.getBirthday() %><br/>
 
   	<br/>--------------------------------<br/>
   	<!-- 用所有的请求参数为bean赋值 -->
   	
   	<jsp:setProperty name="person" property="*"/>
   	
   	<%=person.getName() %><br/>
   	<%=person.getAge() %>
   	
   	<br/>--------------------------------<br/>
   	<jsp:getProperty name="person" property="name"/>
   	<jsp:getProperty name="person" property="age"/>
   	<jsp:getProperty name="person" property="birthday"/>
  </body>
</html>
