package junit.test;
	
import org.junit.Test;

import cn.lnu.dao.BlsDao;
import cn.lnu.dao.MainfaceDao;
import cn.lnu.domain.Bls;
import cn.lnu.domain.Mainface;
import cn.lnu.factory.DaoFactory;
	
public class testDao {
		
	private MainfaceDao dao=DaoFactory.getInstance().createDao(MainfaceDao.class);
	private BlsDao dao1=DaoFactory.getInstance().createDao(BlsDao.class);
	
	
	@Test
	public void findbls(){
		try{
			Bls bean=dao1.find("011");
			System.out.print(bean);
			System.out.print("查找成功！");
		}catch(Exception e){
			e.printStackTrace();
			System.out.print("查找失败！");
		}
	}
	
	@Test
	public void find(){
		try{
			Mainface bean=dao.find("02");
			System.out.print(bean);
			System.out.print("查找成功！");
		}catch(Exception e){
			e.printStackTrace();
			System.out.print("查找失败！");
		}
	}
	@Test
	public void add(){
		try{
			Mainface m=new Mainface("8","新按钮","IDB_new");
			dao.add(m);
			System.out.print("插入成功！");
		}catch(Exception e){
			e.printStackTrace();
			System.out.print("插入失败！");
	}
  }
	@Test
	public void delete(){
		try{
			String btnid="IDB_new";
			dao.delete(btnid);
			System.out.print("删除成功！");
		}catch(Exception e){
			e.printStackTrace();
			System.out.print("删除失败！");
		}
	}
	
	@Test
	public void update(){
		try{
			Mainface m=new Mainface("8","新按钮","IDB_Old");
			dao.update(m);
			System.out.print("更新成功！");
		}catch(Exception e){
			e.printStackTrace();
			System.out.print("更新失败！");
		}
	}
}