package cn.lnu;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.lnu.dao.UserDao;
/*
 servlet调用其他程序，在其他程序中如何读取资源文件(通过类装载器)
 */
public class ServletDemo9 extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		UserDao dao=new UserDao();
		dao.update();
		//dao.find();//测试如何获取服务器上更新之后的配置文件信息
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
