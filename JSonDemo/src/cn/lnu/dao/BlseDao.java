package cn.lnu.dao;

import java.util.List;

import cn.lnu.domain.Blse;

public interface BlseDao {

	void add(Blse b);

	void delete(String btnid);

	void update(Blse b);

	Blse find(String id);

	List<Blse> getAll();

}