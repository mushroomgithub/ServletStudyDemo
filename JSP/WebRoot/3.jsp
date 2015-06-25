<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>JSP 脚本声明</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
    <%!
    	public class Dog{
    		private String name;
    		private int age;
    		public Dog(){
    		}
    		public Dog(String _name,int _age){
    			name=_name;
    			age=_age;
    		}
    		public String getName(){
    			return this.name;
    		}
    		public int getAge(){
    			return this.age;
    		}
    	}
     %>
     
     <%
     	Dog dog=new Dog("小白",2);
     	String name=dog.getName();
     	int age=dog.getAge();
      %>
      
      <pre>小狗的信息：</pre><br/>
      <%=name%><br/>
      <%=age %>
  </body>
</html>
