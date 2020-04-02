package test;

import java.util.ArrayList;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import com.frame.Service;
import com.vo.ForkliftVO;
import com.vo.ItemVO;

public class dbtest {
	public static void main(String[] args) {
		AbstractApplicationContext factory = new GenericXmlApplicationContext("mySpring.xml");
		Service<ForkliftVO> flbiz = (Service)factory.getBean("flservice");
		Service<ItemVO> itbiz = (Service)factory.getBean("itservice");
		ForkliftVO fl1 = new ForkliftVO("id1112", "wh1112", null, "sgld-298-sgl", null);
		ForkliftVO fl2 = new ForkliftVO(null, null, null, null, null);
		ItemVO it1 = new ItemVO();
		it1.setItemid("item1111");
		it1.setItemloc("6,6");
		
			
		
		System.out.println("--- App start ---");
		
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
