package cn.lnu.dao;

import java.util.List;

import cn.lnu.domain.Bls;

public interface BlsDao {

	void add(Bls b);

	void delete(String btnid);

	void update(Bls b);

	Bls find(String id);

	List<Bls> getAll();

}