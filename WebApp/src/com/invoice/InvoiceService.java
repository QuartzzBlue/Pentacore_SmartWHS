package com.invoice;

import java.util.ArrayList;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.frame.Dao;
import com.vo.InvoiceVO;
import com.vo.InvoicedetailVO;

@Service("invservice")
public class InvoiceService implements com.frame.Service<InvoiceVO> {
	
	@Resource(name="invdao")
	Dao<InvoiceVO> invdao;
	
	@Resource(name="invdtldao")
	Dao<InvoicedetailVO> invdtldao;
	
	@Transactional
	@Override
	public void insert(InvoiceVO v) throws Exception {
		
		/*invoice table 입출고 내역 insert*/
		invdao.insert(v);
		
		/*invoicedatail table 입출고 상세내역 insert*/
	
	}

	@Transactional
	@Override
	public void delete(InvoiceVO v) throws Exception {
		invdao.delete(v);
	}
	
	@Transactional
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
