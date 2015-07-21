<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>用户注册</title>
	
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="this is my page">
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    
    <!--<link rel="stylesheet" type="text/css" href="./styles.css">-->
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/ShowCalendar.js"></script>
    
    <script type="text/javascript">
	
		function changeImg(img){
			img.src=img.src+"?"+new Date().getTime();
		}
	</script>
  </head>
  
  <body>
    <form action="${pageContext.request.contextPath }/servlet/RegisterServlet" method="post">
    	用&nbsp;户名：<input type="text" name=username value="${bean.username }"/><span style="color:red;">${bean.errors.username }</span></br></br>
    	密&nbsp;&nbsp;码：<input type="password" name=password value="${bean.password }"/><span style="color:red;">${bean.errors.password }</span></br></br>
    	确认密码：<input type="password" name=password2 value="${bean.password2 }"/><span style="color:red;">${bean.errors.password2 }</span></br></br>
    	邮&nbsp;&nbsp;箱：<input type="text" name="email" value="${bean.email }"/><span style="color:red;">${bean.errors.email }</span><br/></br>
    	生&nbsp;&nbsp;日：<input id="birthday" name="birthday" value="${bean.birthday }" type="text" title="点击选择" onclick="showCalendar(this.id)"><span style="color:red;">${bean.errors.birthday }</span><br/></br>
    	昵&nbsp;&nbsp;称：<input type="text" name="nickname" value="${bean.nickname }"/><span style="color:red;">${bean.errors.nickname }</span><br/></br>
    	验&nbsp;证码：<input type="text" name="checkcode" value="${bean.checkcode }"/>
    	<img src="${pageContext.request.contextPath }/servlet/ImageServlet" onclick="changeImg(this)" alt="换一张" style="cursor:hand"/>
    	<span style="color:red;">${bean.errors.checkcode }</span></br>
    	<input type="submit" value="注册"/>
    	<input type="button" value="登陆"/>
    </form>
  </body>
</html>
