package cn.lnu.dao;

import java.util.List;

import cn.lnu.domain.Upfile;

public interface UpfileDao {

	void add(Upfile upfile);

	List<Upfile> getAll();

	Upfile find(String id);

	void delete(String id);

	void update(Upfile upfile);

}