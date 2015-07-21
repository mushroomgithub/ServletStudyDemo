<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/example" prefix="example"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>开发仿Sun公司的foreach标签</title>
    
  </head>
  
  <body>
  <!-- 模拟充服务器端的servlet带来的数据存在request域中的list属性中 -->
    <%
    	List list=new ArrayList();
    	list.add("aaaa");
    	list.add("bbbb");
    	list.add("cccc");
    	list.add("dddd");
    	request.setAttribute("list",list);
     %>
     <example:foreach2 items="${list}" var="str">
     	${str }
     </example:foreach2>
     <br/>--------------------------------------------<br/>
     <%
     	Map map=new HashMap();
     	map.put("aa","111");
     	map.put("bb","222");
     	map.put("cc","333");
     	request.setAttribute("map",map);
      %>
     <example:foreach2 items="${map}" var="entry">
     	${entry.key } = ${entry.value }
     </example:foreach2>
     <br/>--------------------------------------------<br/>
      <%
     	Integer num[]={1,2,3,4};
     	request.setAttribute("num",num);
      %>
     <example:foreach2 items="${num}" var="i">
     	${i}
     </example:foreach2>
       <br/>--------------------------------------------<br/>
     <%
     	double arr[]={1,2,3,4};
     	request.setAttribute("arr",arr);
      %>
     <example:foreach2 items="${arr}" var="i">
     	${i}
     </example:foreach2>
  </body>
</html>
