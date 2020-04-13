package test;

import java.util.ArrayList;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import com.frame.Service;
import com.invoice.InvoiceService;
import com.vo.ForkliftVO;
import com.vo.InvoiceVO;
import com.vo.ItemVO;
import com.vo.WarehouseVO;

public class dbtest {
	public static void main(String[] args) {
		AbstractApplicationContext factory = new GenericXmlApplicationContext("mySpring.xml");
		Service<ForkliftVO> flbiz = (Service)factory.getBean("flservice");
		Service<ItemVO> itbiz = (Service)factory.getBean("itservice");
		Service<InvoiceVO> ivbiz = (Service)factory.getBean("invservice");
		Service<WarehouseVO> whbiz = (Service)factory.getBean("whservice");
		
		ForkliftVO fl1 = new ForkliftVO("id1112", "wh1112", null, "sgld-298-sgl", null);
		ForkliftVO fl2 = new ForkliftVO(null, null, null, null, null);
		ItemVO it1 = new ItemVO();
		InvoiceVO iv1 = new InvoiceVO();
		WarehouseVO wh1 = new WarehouseVO();
		WarehouseVO wh2 = new WarehouseVO("wh1112", "천안 제1물류창고", "냉동식품", 1424.1, "냉동창고");
		
		
//		it1.setItemid("item1112");
//		it1.setWareid("wh1111");
//		it1.setItemloc("6,6");
		
			
		
		System.out.println("--- App start ---");
		
//		ArrayList<InvoiceVO> list = null;
//		
//		try {
//			list = ivbiz.selectAll(iv1);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		System.out.println(list.toString());
		
		
//		ArrayList<WarehouseVO> list = null;
//		try {
//			whbiz.insert(wh2);
//			System.out.println(";;");
//			list = whbiz.selectAll(wh1);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		System.out.println(list.toString());
		System.out.println("App End");
		
//		ArrayList<ItemVO> list = null;
//		try {
//			list = itbiz.selectAll(it1);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		System.out.println(list.toString());
		
		
//		try {
//			itbiz.update(it1);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		//insert
//		try {
//			biz.insert(fl1);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		//delete
//		try {
//			biz.delete(new ForkliftVO("id1112", null, null, null, null));
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		
//		selectAll
//		try {
//			ArrayList<ForkliftVO> list = null;
//			list = biz.selectAll(fl2);
//			for(ForkliftVO f : list) {
//				System.out.println(f);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		
		factory.close();
	}
}
