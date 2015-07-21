package cn.lnu.service;

import java.util.List;

import cn.lnu.dao.CategoryDao;

public class BusinessService {
	public List getAllCategory(){
		CategoryDao dao=new CategoryDao();
		return dao.getAll();
	}
}
