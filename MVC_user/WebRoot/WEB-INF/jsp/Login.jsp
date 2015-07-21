<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
 <head>
	
		<title>网站登陆页面</title>
		<link rel="styleSheet" type="text/css" href="${pageContext.request.contextPath }/CSS/login.css"/>
	</head>
	
	<body>
		<div id="container">
			<div id="login">
				<div id="form">
					<form action="${pageContext.request.contextPath }/servlet/LoginServlet" method="post">
						<div  id="input">
						用户：<input type="text" name="username" /><br/><br/>
						密码：<input type="password" name="password"/><br/><br/>
						</div>
						<div id="btn">
						<input type="submit" value="登陆"/>
						</div>
					</form>
				</div>
			</div>
		</div>
		
	</body>
</html>
