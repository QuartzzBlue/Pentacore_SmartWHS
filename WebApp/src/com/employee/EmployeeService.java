package com.employee;

import java.util.ArrayList;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.frame.Dao;
import com.vo.EmployeeVO;

@Service("empservice")
public class EmployeeService implements com.frame.Service<EmployeeVO> {

	@Resource(name="empdao")
	Dao<EmployeeVO> dao;
	
	@Transactional
	@Override
	public void insert(EmployeeVO v) throws Exception {
		dao.insert(v);
	}

	@Transactional
	@Override
	public void delete(EmployeeVO v) throws Exception {
		dao.delete(v);
	}

	@Transactional
	@Override
	public void update(EmployeeVO v) throws Exception {
		dao.update(v);
	}

	@Override
	public EmployeeVO select(EmployeeVO v) throws Exception {
		return dao.select(v);
	}

	@Override
	public ArrayList<EmployeeVO> selectAll(EmployeeVO v) throws Exception {
		return dao.selectAll(v);
	}

}
