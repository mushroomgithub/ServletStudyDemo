package cn.lnu.service;

import java.util.List;

import cn.lnu.domain.Upfile;

public interface BusinessService {

	void addUpfile(Upfile upfile);

	List getAllUpfile();

	//通过文件id查找上传文件
	Upfile finUpfile(String id);

}