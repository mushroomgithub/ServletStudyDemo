<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="cn.lnu.domain.Person"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>测试jstl中的c:out标签</title>
    
  </head>
  
  <body> 
  
  <% 
  	request.setAttribute("data",null); 
   %>
   <br>------------------c:out------------------------<br>
   <c:out value="${data}" default="daa" escapeXml="true"></c:out><br>
  	<c:out value="<a href=''>点我</a>" default="" escapeXml="true"></c:out>
  	
  	<br>------------------c:set可以操作各个域javabean Map集合------------------------<br>
  	<c:set var="data" value="aaaa" scope="page"></c:set>
  	${data }<br>
  	
  	<%
  		Map map=new HashMap();
  		map.put("aa","111");
  		request.setAttribute("map",map);
  	 %>
  	<c:set property="dd" value="falfg" target="${map}" ></c:set>
  	${map.dd }<br>
  	
  	<%
  		Person p=new Person();
  		p.setName("mushroom");
  		request.setAttribute("p",p);
  	 %>
  	 <c:set property="name" value="sss" target="${p}"></c:set>
  	 ${p.name }
  	 
  	 <br>------------------c:catch-可以抓异常不让异常抛给用户-----------------------<br>
  	 <c:catch var="myex">
	  	 <%
	  	 	int x=10/0;
	  	  %>
  	  </c:catch>
  	  ${myex.message }
  	  
  	  <br>---------------c:if-------------------------<br>
  	  
  	  <c:if  var="str" test="${username==null}" scope="page">
  	  	empty
  	  </c:if>
  	  ${str }
  	  
  	  <br>---------------c:foreach-可以做迭代------------------------<br>
  	 	<%
  	 	List list=new ArrayList();
  	 	list.add("aaa");
  	 	list.add("bbb");
  	 	list.add("ccc");
  	 	request.setAttribute("list",list);
  	 	 %>
  	 	 
  	 	 <c:forEach var="str" items="${list}">
  	 	 	${str }
  	 	 </c:forEach>
  	 	 <br>
  	 	 
  	 	 <c:forEach var="num" begin="1" end="9" step="1">
  	 	 ${num }
  	 	 </c:forEach>
  	 	 
  	 	 <br>---------------c:foreach实现表格间色显示------------------------<br>
  	 	
  	 	 <%
  	 	List lt=new ArrayList();
  	 	lt.add("aaa");
  	 	lt.add("bbb");
  	 	lt.add("ccc");
  	 	lt.add("ccc");
  	 	lt.add("ccc");
  	 	lt.add("ccc");
  	 	lt.add("ccc");
  	 	lt.add("ccc");
  	 	request.setAttribute("list1",lt);
  	 	 %>
  	 	
  	 	 <%-- ${status}获得到了一个对象，这个对象记住了当前是第几次迭代--%>
  	 	 <style>
  	 	 	.odd{
  	 	 		background-color:#FF99FF;
  	 	 	}
  	 	 	.even{
  	 	 		background-color:#FF6633;
  	 	 	}
  	 	 	
  	 	 	tr:hover{
  	 	 		background-color:blue;
  	 	 	}
  	 	 </style>
  	 	 <table border="1" width="30%">
  	 	 	<c:forEach var="str" items="${list1}" varStatus="status">
  	 	 		<tr class="${status.count%2==0?'even':'odd' }">	
  	 	 			<td>${str }</td>
  	 	 		</tr>
  	 	 	</c:forEach>
  	 	 </table>
  	 	
  	 	  <br>---------------c:url构建地址(禁用cookie时，第一次访问还会带sessionid过去)------------------------<br>
  	 	 	<c:url var="index" value="/index.jsp">
  	 	 		<c:param name="name" value="小蘑菇"></c:param>
  	 	 	</c:url>
  	 	  <a href="${index  }">购买</a>
  	 	  
  	 	  <br>----------------c:forTokens分割字符串------------------------------<br>
  	 	  <%
  	 	  	request.setAttribute("data1","aa,bb,cc,dd");
  	 	   %>
  	 	   
  	 	   <c:forTokens  var="str" items="${data1}" delims="','">
  	 	   	${str }
  	 	   </c:forTokens>
  </body>
</html>
