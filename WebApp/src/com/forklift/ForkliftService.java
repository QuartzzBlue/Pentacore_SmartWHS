package com.forklift;

import java.util.ArrayList;

import javax.annotation.Resource;

import com.frame.Dao;
import com.frame.Service;
import com.vo.ForkliftVO;

@org.springframework.stereotype.Service("flservice")
public class ForkliftService implements Service<ForkliftVO> {

	@Resource(name="fldao")
	Dao<ForkliftVO> dao;
	
	@Override
	public void insert(ForkliftVO v) throws Exception {
		dao.insert(v);
	}

	@Override
	public void delete(ForkliftVO v) throws Exception {
		dao.delete(v);
	}

	@Override
	public void update(ForkliftVO v) throws Exception {
		dao.update(v);
	}

	@Override
	public ForkliftVO select(ForkliftVO v) throws Exception {
		return dao.select(v);
	}

	@Override
	public ArrayList<ForkliftVO> select() throws Exception {
		return dao.select();
	}

}
