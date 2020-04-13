package com.invoicedetail;

import java.util.ArrayList;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.frame.Dao;
import com.vo.InvoicedetailVO;

@Service("invdtlservice")
public class InvoicedetailService implements com.frame.Service<InvoicedetailVO> {

	@Resource(name="invdtldao")
	Dao<InvoicedetailVO> invdtldao;
	
//	@Resource(name="itdao")
//	Dao<ItemVO> itdao;
	
	
	@Transactional
	@Override
	public void insert(InvoicedetailVO v) throws Exception {
		
		
		/*재고관리*/
//		ItemVO updItem = new ItemVO();
		
//		//item DB 업데이트를 위해 item id 받아옴
//		updItem.setItemid(v.getItemid());
//		
//		//재고 관리를 위해 기존 item DB에 있던 item 재고 수량 가져옴
//		ItemVO getItem = itdao.select(updItem);
//		int tempstock = getItem.getItemstock();
//			//입고
//			if(v.getInvoicestat().equals("Receiving")) tempstock += v.getInvoiceqty();
//			//출고
//			else if(v.getInvoicestat().equals("Shipping")) tempstock -= v.getInvoiceqty();
//		updItem.setItemstock(tempstock);
//		
//		//invoice DB에 들어갈 데이터 컬럼 채워줌
//		v.setWarename(getItem.getWarename());
		
//		//item DB 업데이트
//		itdao.update(updItem);
		
		
		invdtldao.insert(v);
	}

	@Transactional
	@Override
	public void delete(InvoicedetailVO v) throws Exception {
		invdtldao.delete(v);
	}

	@Transactional
	@Override
	public void update(InvoicedetailVO v) throws Exception {
		invdtldao.update(v);
	}

	@Override
	public InvoicedetailVO select(InvoicedetailVO v) throws Exception {
		return invdtldao.select(v);
	}

	@Override
	public ArrayList<InvoicedetailVO> selectAll(InvoicedetailVO v) throws Exception {
		return invdtldao.selectAll(v);
	}

}
