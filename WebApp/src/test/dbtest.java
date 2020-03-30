package test;

import java.util.ArrayList;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import com.frame.Service;
import com.vo.ForkliftVO;

public class dbtest {
	public static void main(String[] args) {
		AbstractApplicationContext factory = new GenericXmlApplicationContext("mySpring.xml");
		Service<ForkliftVO> biz = (Service)factory.getBean("flservice");
		
		ForkliftVO fl1 = new ForkliftVO("id1112", "wh1112", null, "sgld-298-sgl", null);
		ForkliftVO fl2 = new ForkliftVO(null, null, null, null, null);
		System.out.println("--- App start ---");
		try {
			ArrayList<ForkliftVO> list = null;
			list = biz.selectAll(fl2);
			for(ForkliftVO f : list) {
				System.out.println(f);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		factory.close();
	}
}
