package cn.lnu.dao;

import java.util.List;

import cn.lnu.domain.Xffsface1;

public interface XffsFace1Dao {

	void add(Xffsface1 x1);

	void delete(String btnid);

	void update(Xffsface1 x1);

	Xffsface1 find(String id);

	List<Xffsface1> getAll();

}