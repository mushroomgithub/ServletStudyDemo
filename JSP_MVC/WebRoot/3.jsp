<%@ page language="java" import="java.util.*, cn.lnu.domain.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>el表达式</title>
    
  </head>
  
  <body>
    	<%
    		String data="mushroom";
    		request.setAttribute("data",data);
    	 %>
    	 <!-- 这个el表达式在翻译为servlet时，会转换为pageContext.findAttribute("data"); 它查找的顺序是 page request session application 如果在所有域中均找不到，返回“”，不会对页面显示造成影响-->
    	 ${data }
    	 <br>
    	 
    	 <%
    	 	Person p=new Person();
    	 	p.setName("Jhon");
    	 	request.setAttribute("person",p);
    	  %>
    	  
    	  ${person.name }
    	  <br/>
    	  <%
    	  	Person p1=new Person();
    	 	Address a=new Address();
    	 	a.setCity("北京");
    	 	p1.setAddress(a);
    	 	
    	 	request.setAttribute("p1",p1);
    	   %>
    	   
    	   ${p1.address.city }
    	   
    	   <br/>
    	   
    	   <%
    	   	List list=new ArrayList();
    	   	list.add(new Person("dada"));
    	   	list.add(new Person("fafa"));
    	   	list.add(new Person("gaga"));
    	   	
    	   	request.setAttribute("list",list);
    	    %>
    	    
    	    ${list[2].name }
    	   
    	   <br/>
    	   
    	   <%
    	   	Map map=new HashMap();
    	   	map.put("aa",new Person("aaaaaa"));
    	   	map.put("bb",new Person("bbbbbb"));
    	   	map.put("cc",new Person("cccccc"));
    	   	map.put("dd",new Person("dddddd"));
    	   	
    	   	request.setAttribute("map",map);
    	    %>
    	    <!-- 使用el表达式取数据时一般使用. .取不出来数据时可以使用[] -->
    	    ${map.bb.name }
    	    ${map['dd'].name }
    	    
    	    <!--
    	    	使用el表达式获得当前web应用的名称,此时会得到/JSP_MVC
    	    -->
    	    ${pageContext.request.contextPath}
  </body>
</html>
