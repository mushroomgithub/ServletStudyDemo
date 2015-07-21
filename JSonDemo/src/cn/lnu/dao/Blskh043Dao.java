package cn.lnu.dao;

import java.util.List;

import cn.lnu.domain.Blskh043;

public interface Blskh043Dao {

	void add(Blskh043 b);

	void delete(String btnid);

	void update(Blskh043 b);

	Blskh043 find(String id);

	List<Blskh043> getAll();

}