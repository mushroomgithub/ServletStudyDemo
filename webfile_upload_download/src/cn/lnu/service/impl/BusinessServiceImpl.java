package cn.lnu.service.impl;

import java.util.List;

import cn.lnu.dao.UpfileDao;
import cn.lnu.domain.Upfile;
import cn.lnu.factory.DaoFactory;
import cn.lnu.service.BusinessService;

public class BusinessServiceImpl implements BusinessService {
	
	private UpfileDao dao=DaoFactory.getInstance().createDao(UpfileDao.class);//使用工厂模式产生一个dao
	
	public void addUpfile(Upfile upfile){//将上传文件信息加入到数据库中
		dao.add(upfile);
	}
	
	public List getAllUpfile(){
		return dao.getAll();
	}
	//通过文件id查找上传文件
	public Upfile finUpfile(String id){
		return dao.find(id);
	}
}
