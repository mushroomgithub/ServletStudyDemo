<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!-- 添加用户视图界面 -->
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>添加用户的视图</title>
    
    <script type="text/javascript" src="${pageContext.request.contextPath }/js/ShowCalendar.js"></script>
    
    <script type="text/javascript">
    	function makepre(){
    		var preference="";
    		var pres=document.getElementsByName("pre");
    		for(var i=0;i<pres.length;i++){
    			var input=pres[i];
    			if(input.checked==true){
    				preference+=input.value+",";
    			}
    		}
    		preference=preference.substr(0,preference.length-1);
    		
    		var form=document.getElementById("form");
    		var input=document.createElement("input");
    		input.type="hidden";
    		input.name="preference";
    		input.value=preference;
    		//将多选框中的选项组合成一个字符串，然后以在form中加一个隐藏项的方式在提交时带到后端服务器
    		form.appendChild(input);
    		return true;
    	}
    </script>
    
  </head>
  
  <body style="text-align:center;">
    <form action="${pageContext.request.contextPath }/servlet/AddCustomerServlet" id="form" method="post" onsubmit="return makepre()">
	    <table border="1" width="50%">
	    	<tr>
	    		<td>客户名：</td>
	    		<td><input type="text" name="name"></td>
	    	</tr>
	    	<tr>
	    		<td>性别：</td>
	    		<td>
	    			<c:forEach  var="gender" items="${genders}">
	    			<input type="radio" name="gender" value="${gender}">${gender }
	    			</c:forEach>
	    		</td>
	    	</tr>
	    	<tr>
	    		<td>生日：</td>
	    		<td>
	    			<input name="birthday" type="text" id="birthday" title="点击选择" onClick="showCalendar(this.id)"> 
	    		</td>
	    	</tr>
	    	<tr>
	    		<td>手机号：</td>
	    		<td>
	    			<input type="text" name="cellphone">
	    		</td>
	    	</tr>
	    	<tr>
	    		<td>邮箱：</td>
	    		<td>
	    			<input type="text" name="email">
	    		</td>
	    	</tr>
	    	<tr>
	    		<td>爱好：</td>
	    		<td>
	    			<c:forEach var="preference" items="${preferences}">
	    				<input type="checkbox" name="pre" value="${preference }">${preference }
	    			</c:forEach>
	    		</td>
	    	</tr>
	    	<tr>
	    		<td>客户类型：</td>
	    		<td>
	    			<c:forEach var="type" items="${types}">
	    				<input type="radio" name="type" value="${type }">${type }
	    			</c:forEach>
	    		</td>
	    	</tr>
	    	<tr>
	    		<td>备注：</td>
	    		<td>
	    			<textarea rows="5" cols="60" name="description"></textarea>
	    		</td>
	    	</tr>
	    	<tr>
	    		<td>
	    		<input type="submit" value="添加客户">
	    		</td>
	    		<td>
	    			<input type="reset" value="重置">
	    		</td>
	    	</tr>
	    </table>
    </form>
  </body>
</html>
