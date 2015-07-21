<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>显示商品分类树页面</title>
    <!-- 在页面将传递过来的list集合中的分类数据,以树的格式显示，可以使用js的xtree插件,或者jquery的插件都可以轻松实现，这里使用js的xtree插件实现商品分类树的显示 -->
    <script src="${pageContext.request.contextPath }/js/xtree.js"></script>
	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath }/css/xtree.css">
  </head>
  
  <body>
  	<script type="text/javascript">
		  <c:forEach var="c" items="${list}">
		  		<c:if test='${c.depth==1 }'>
		  			var tree = new WebFXTree('${c.name}');
		  		</c:if>
		  		
		  		<c:if test='${c.depth==2 }'>
		  			var node${c.depth} = new WebFXTreeItem('${c.name}');   //node2
		  			tree.add(node${c.depth});
		  		</c:if>
		  		
		  		<c:if test='${c.depth>2 }'>
		  			var node${c.depth} = new WebFXTreeItem('${c.name}');  //node3
		  			node${c.depth-1}.add(node${c.depth});
		  		</c:if>
		  		
		  </c:forEach>	
			document.write(tree);
  	</script>
  	
  	
  	
  </body>
</html>

