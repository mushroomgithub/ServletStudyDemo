package cn.lnu.dao;

import java.util.List;

import cn.lnu.domain.Blskh;

public interface BlskhDao {

	void add(Blskh b);

	void delete(String btnid);

	void update(Blskh b);

	Blskh find(String id);

	List<Blskh> getAll();

}