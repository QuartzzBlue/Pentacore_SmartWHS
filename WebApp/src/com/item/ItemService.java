package com.item;

import java.util.ArrayList;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.frame.Dao;
import com.vo.ItemVO;


@Service("itservice")
public class ItemService implements com.frame.Service<ItemVO> {

	@Resource(name="itdao")
	Dao<ItemVO> dao;
	
	String wareNameList[] = {"이천 제1물류창고"};
	
	@Transactional
	@Override
	public void insert(ItemVO v) throws Exception {
		
		int index = Integer.parseInt(v.getWarename().substring(4,6));
		v.setWarename(wareNameList[index]);
		dao.insert(v);
	}
	
	@Transactional
	@Override
	public void delete(ItemVO v) throws Exception {
		
		int index = Integer.parseInt(v.getWarename().substring(4,6));
		v.setWarename(wareNameList[index]);
		dao.delete(v);
	}

	@Transactional
	@Override
	public void update(ItemVO v) throws Exception {
		int index = Integer.parseInt(v.getWarename().substring(4,6));
		v.setWarename(wareNameList[index]);
		dao.update(v);
	}

	@Override
	public ItemVO select(ItemVO v) throws Exception {
		int index = Integer.parseInt(v.getWarename().substring(4,6));
		v.setWarename(wareNameList[index]);
		return dao.select(v);
	}

	@Override
	public ArrayList<ItemVO> selectAll(ItemVO v) throws Exception {
		int index = Integer.parseInt(v.getWarename().substring(4,6));
		v.setWarename(wareNameList[index]);
		return dao.selectAll(v);
	}

}
