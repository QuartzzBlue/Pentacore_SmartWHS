package com.invoice;

import java.util.ArrayList;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.frame.Dao;
import com.vo.InvoiceVO;
import com.vo.ItemVO;

@Service("invservice")
public class InvoiceService implements com.frame.Service<InvoiceVO> {

	@Resource(name="invdao")
	Dao<InvoiceVO> invdao;
	
	@Resource(name="itdao")
	Dao<ItemVO> itdao;
	
	@Override
	public void insert(InvoiceVO v) throws Exception {
		ItemVO updItem = new ItemVO();
		
		//item DB 업데이트를 위해 item id 받아옴
		updItem.setItemid(v.getItemid());
		
		//재고 관리를 위해 기존 item DB에 있던 item 재고 수량 가져옴
		ItemVO getItem = itdao.select(updItem);
		int tempstock = getItem.getItemstock();
			//입고
			if(v.getInvoicestat().equals("Receiving")) tempstock += v.getInvoiceqty();
			//출고
			else if(v.getInvoicestat().equals("Shipping")) tempstock -= v.getInvoiceqty();
		updItem.setItemstock(tempstock);
		
		//invoice DB에 들어갈 데이터 컬럼 채워줌
		v.setWarename(getItem.getWarename());
		
		//item DB 업데이트
		itdao.update(updItem);
		
		//invoice DB 입출고 내역 insert
		invdao.insert(v);
	
	}

	@Override
	public void delete(InvoiceVO v) throws Exception {
		invdao.delete(v);
	}

	@Override
	public void update(InvoiceVO v) throws Exception {
		invdao.update(v);
	}

	@Override
	public InvoiceVO select(InvoiceVO v) throws Exception {
		return invdao.select(v);
	}

	@Override
	public ArrayList<InvoiceVO> selectAll(InvoiceVO v) throws Exception {
		
		return invdao.selectAll(v);
	}

}
