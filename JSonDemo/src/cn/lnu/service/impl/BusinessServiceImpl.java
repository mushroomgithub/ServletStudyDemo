package cn.lnu.service.impl;

import cn.lnu.dao.BlsDao;
import cn.lnu.dao.BlseDao;
import cn.lnu.dao.Blskh042Dao;
import cn.lnu.dao.Blskh043Dao;
import cn.lnu.dao.Blskh044Dao;
import cn.lnu.dao.BlskhDao;
import cn.lnu.dao.MainfaceDao;
import cn.lnu.dao.XffsFace1Dao;
import cn.lnu.domain.Bls;
import cn.lnu.domain.Blse;
import cn.lnu.domain.Blskh;
import cn.lnu.domain.Blskh042;
import cn.lnu.domain.Blskh043;
import cn.lnu.domain.Blskh044;
import cn.lnu.domain.Mainface;
import cn.lnu.domain.Xffsface1;
import cn.lnu.factory.DaoFactory;
import cn.lnu.service.BusinessService;

public class BusinessServiceImpl implements BusinessService {
	
	private MainfaceDao dao=DaoFactory.getInstance().createDao(MainfaceDao.class);
	private XffsFace1Dao dao1=DaoFactory.getInstance().createDao(XffsFace1Dao.class);
	
	private BlsDao dao2=DaoFactory.getInstance().createDao(BlsDao.class);
	private BlseDao dao3=DaoFactory.getInstance().createDao(BlseDao.class);
	private BlskhDao dao4=DaoFactory.getInstance().createDao(BlskhDao.class);
	private Blskh042Dao dao5=DaoFactory.getInstance().createDao(Blskh042Dao.class);
	private Blskh043Dao dao6=DaoFactory.getInstance().createDao(Blskh043Dao.class);
	private Blskh044Dao dao7=DaoFactory.getInstance().createDao(Blskh044Dao.class);
	public Mainface find(String id){
		return dao.find(id);
	}
	
	public Xffsface1 find1(String id){
		return dao1.find(id);
	}
	
	
	public Bls find2(String id){
		return dao2.find(id);
	}
	
	public Blse find3(String id){
		return dao3.find(id);
	}
	
	public Blskh find4(String id){
		return dao4.find(id);
	}
	
	public Blskh042 find5(String id){
		return dao5.find(id);
	}
	
	public Blskh043 find6(String id){
		return dao6.find(id);
	}
	public Blskh044 find7(String id){
		return dao7.find(id);
	}
}
