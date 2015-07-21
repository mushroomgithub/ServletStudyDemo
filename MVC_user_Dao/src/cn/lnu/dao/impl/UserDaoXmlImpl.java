package cn.lnu.dao.impl;

import java.text.SimpleDateFormat;

import org.dom4j.Document;
import org.dom4j.Element;

import cn.lnu.dao.UserDao;
import cn.lnu.domain.User;
import cn.lnu.utils.XmlUtils;
//实现用的注册和登陆
public class UserDaoXmlImpl implements UserDao {
	//增加一个新用户
	public void addUser(User user){
		try {
			Document document=XmlUtils.getDocument();
			Element root=document.getRootElement();
			Element user_tag=root.addElement("user");
			user_tag.setAttributeValue("id", user.getId());
			user_tag.setAttributeValue("username", user.getUsername());
			user_tag.setAttributeValue("password", user.getPassword());
			user_tag.setAttributeValue("email", user.getEmail());
			user_tag.setAttributeValue("birthday",user.getBirthday()==null?"" : user.getBirthday().toLocaleString());
			user_tag.setAttributeValue("nickname", user.getNickname());
			
			XmlUtils.writeToXml(document);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}
	//根据用户名和密码查找用户
	public User find(String username,String password){
		try {
			Document document=XmlUtils.getDocument();
			//xpath表达式搜索
			Element e=(Element) document.selectSingleNode("//user[@username='"+username+"' and @password='"+password+"']");
			
			if(e==null){
				return null;
			}
			
			User user=new User();
			user.setId(e.attributeValue("id"));
			user.setUsername(e.attributeValue("username"));
			user.setPassword(e.attributeValue("password"));
			user.setEmail(e.attributeValue("email"));
			
			String birthday=e.attributeValue("birthday");
			if(birthday.equals("")||birthday==null){
				user.setBirthday(null);
			}else{
				SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
				user.setBirthday(df.parse(birthday));
			}
			
			user.setNickname(e.attributeValue("nickname"));
			return user;
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}
	//查找注册的用户是否在数据库中已经存在
	public boolean find(String username){
		try {
			Document document=XmlUtils.getDocument();
			//xpath表达式搜索
			Element e=(Element) document.selectSingleNode("//user[@username='"+username+"']");
			
			if(e==null){
				return false;
			}
			
			return true;
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}
	
}
