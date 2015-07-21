package cn.lnu.dao;

import java.util.List;

import cn.lnu.domain.Blskh044;

public interface Blskh044Dao {

	void add(Blskh044 b);

	void delete(String btnid);

	void update(Blskh044 b);

	Blskh044 find(String id);

	List<Blskh044> getAll();

}