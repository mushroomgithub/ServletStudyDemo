<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>在web页面中添加动态上传输入项</title>
    
    <script type="text/javascript">
    	function addfile(){
    		var filess=document.getElementById("files");
    		var input=document.createElement("input");
    		input.type='file';
    		input.name='file';
    		
    		//在生成一个button输入项,
    		var btn=document.createElement("input");
    		btn.type='button';
    		btn.value='删除';
    		btn.onclick=function del(){//新创建的按钮响应一个函数，完成删除操作
    			this.parentNode.parentNode.removeChild(this.parentNode);
    		}
    		
    		//动态创建一个div,将新创建的上传输入项和删除按钮放在这个div，便于管理，即方便整体删除
    		var div=document.createElement("div");
    		div.appendChild(input);
    		div.appendChild(btn);
    		
    		files.appendChild(div);
    	}
    </script>
  </head>
  
  <body>
  <form action="${pageContext.request.contextPath }/servlet/UploadServlet2" enctype="multipart/form-data" method="post">
    <table border="0">
    	<tr>
    		<td>上传用户:</td>
    		<td>
    			<input type="text" name="username" />
    		</td>
    	</tr>
    	
    	<tr>
    		<td>上传文件:</td>
    		<td>
    			<input type="button"  value="添加上传文件" onclick="addfile()" />
    		</td>
    	</tr>
    	
    	<tr>
    		<td colspan="2">
    			<div id="files">
    			
    			</div>
    		</td>
    	</tr>
    	
    	<tr>
    		<td colspan="2">
    			<input type="submit" value="上传">
    		</td>
    	</tr>
    </table>
   </form>
  </body>
</html>
