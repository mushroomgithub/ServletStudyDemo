package cn.lnu.dao;

import java.util.List;

import cn.lnu.domain.Mainface;

public interface MainfaceDao {

	void add(Mainface m);

	void delete(String btnid);

	void update(Mainface m);

	Mainface find(String id);

	List<Mainface> getAll();

}