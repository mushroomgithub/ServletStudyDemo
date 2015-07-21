<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>显示学生分页数据</title>
  </head>
  
  <body>
  
  	<c:forEach var="s" items="${pagebean.list}">
  		${s.id } ${s.name } <br/>
  	</c:forEach>
  
  	
     <script type="text/javascript">
    	function gotopage(currentpage){  	
    		var pagesize = document.getElementById("pagesize").value;
    		window.location.href = '${pageContext.request.contextPath}/servlet/ListStudentServlet?currentpage=' + currentpage + '&pagesize=' + pagesize;
    	}
    </script>
    
    共[${pagebean.totalrecord }]条记录,
    每页<input type="text" id="pagesize" value="${pagebean.pagesize }" onchange="gotopage(${pagebean.currentpage })" style="width: 30px" maxlength="2">条,
    共[${pagebean.totalpage }]页,
    当前[${pagebean.currentpage }]页
    &nbsp;&nbsp;&nbsp;
    <a href="javascript:void(0)" onclick="gotopage(${pagebean.previouspage })">上一页</a>
	    <c:forEach var="pagenum" items="${pagebean.pagebar}">
	    	<c:if test="${pagenum==pagebean.currentpage}">
	    		<font color="red">${pagenum }</font>
	    	</c:if>
	    	
	    	<c:if test="${pagenum!=pagebean.currentpage}">
	    		<a href="javascript:void(0)" onclick="gotopage(${pagenum })">${pagenum }</a>
	    	</c:if>
	    </c:forEach>
    <a href="javascript:void(0)" onclick="gotopage(${pagebean.nextpage })">下一页</a>
    <input type="text" id="pagenum" style="width: 30px">
    <input type="button" value=" GO " onclick="gotopage(document.getElementById('pagenum').value)">
  </body>
</html>
