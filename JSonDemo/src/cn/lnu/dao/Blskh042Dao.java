package cn.lnu.dao;

import java.util.List;

import cn.lnu.domain.Blskh042;

public interface Blskh042Dao {

	void add(Blskh042 b);

	void delete(String btnid);

	void update(Blskh042 b);

	Blskh042 find(String id);

	List<Blskh042> getAll();

}