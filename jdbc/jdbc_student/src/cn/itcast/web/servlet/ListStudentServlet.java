package cn.itcast.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.domain.PageBean;
import cn.itcast.domain.QueryInfo;
import cn.itcast.service.BusinessService;
import cn.itcast.utils.WebUtils;

public class ListStudentServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		QueryInfo info = WebUtils.request2Bean(request, QueryInfo.class);
		BusinessService service = new BusinessService();
		PageBean bean = service.pageQuery(info);
		request.setAttribute("pagebean", bean);
		request.getRequestDispatcher("/liststudent.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
