package com.controller;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.Response;

import org.codehaus.jackson.map.ObjectMapper;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.frame.Service;
import com.vo.ForkliftVO;
import com.vo.ItemVO;

import msg.ForkLift;

@Controller
public class ForkliftController {
	@Resource(name = "flservice")
	Service<ForkliftVO> flservice;
	
	
//	@RequestMapping("/fllist.pc")
//	public ModelAndView fllist(ModelAndView mv, HttpServletRequest request, HttpServletResponse response) {
//
//		
//		return mv;
//	}

	@ResponseBody 
	@RequestMapping("/distance.pc")
	public void distancefl(HttpServletResponse res) throws IOException {
		
		//밑에는 예시고 JSON 형태안에 JSON을 또담아서 id와 distance가 변수 하나로 갈 수 있게끔 하자
		JSONObject distInput = new JSONObject();
		distInput.put("forklift1", "[74254, 43445, 62341, 58903, 72345, 63452, 67543]");
		distInput.put("forklift2", "[68315, 76473, 73245, 75334, 68432, 34260, 71435]");
		distInput.put("forklift3", "[73621, 67258, 73589, 82351, 52127, 72357, 80032]");
		distInput.put("forklift4", "[62318, 76235, 67542, 45611, 73012, 73516, 74525]");
		
		PrintWriter out = res.getWriter();
		System.out.println("distInput is " +distInput.toJSONString());
	    out.print(distInput.toJSONString());
	    out.close();
	    
	}
	
	
	
	JSONObject jo = new JSONObject();
	@ResponseBody //input 에 Requestbody 지워준다
	@RequestMapping("/sendfl.pc")
	public void receivefl(HttpServletResponse res) throws IOException {

		PrintWriter out = res.getWriter();
		System.out.println("output is " +jo.toJSONString() );
	    out.print(jo.toJSONString());
	    out.close();
	}
	
	
	
	
	static HashMap<String, Object> flJsonList = new HashMap<>();
//	@ResponseBody //input 에 Requestbody 지워준다
	@RequestMapping("/receivefl.pc")
	public void receivefl(HttpServletResponse res, @RequestBody String input) throws IOException {
		JSONParser parser = new JSONParser();
		try {
			JSONObject obj = (JSONObject)parser.parse(input);
			System.out.println("ojb : "+obj.toJSONString());
			String id = (String)obj.get("forkliftid");
			jo.put(id, input);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		
		
//	public void receivefl(HttpServletResponse res) throws IOException {
//	
//	 
//	
//		System.out.println("====/receivefl started====");
//		
//		
//		String input = "{\"forkliftid\" : \"forklift2\", \"status\" : 2, \"battery\" : 333, \"temperature\" : 20, \"distanceDriven\" : 300}";
//		 
//		
//		res.setContentType("text/json;charset=UTF-8");
//	    json=input;
//		PrintWriter out = res.getWriter();
//	    
//	    
//	    System.out.println("====received====");
//		JSONParser parser = new JSONParser(); 
//		Object obj = null;
//		
//		System.out.println("input is " +json );
//		try {
//			obj = parser.parse(input);
//			JSONObject flObject = (JSONObject) obj; 
//			
//			System.out.println(obj);
//			
//			System.out.println(flObject);
//			
//			flJsonList.put((String) flObject.get("forkliftid"), flObject);
//		} catch (ParseException e) {
//			e.printStackTrace();           
//		} 
//
//	    //flJsonList.put("forkLocX", "jmj"); 
//	    
//		   
//	    JSONObject jo = new JSONObject();
//        for( Map.Entry<String, Object> entry : flJsonList.entrySet() ) {
//            String key = entry.getKey();
//            Object value = entry.getValue();
//            jo.put(key, value);
//        }

////	    out.print(jo);
//	    out.print(json);
//	    out.close();
	    
	    
	/*    
	@RequestMapping("/receivefl.pc")
	@ResponseBody
	public void receivefl(HttpServletResponse res, @RequestBody String input) throws IOException { 
		
		System.out.println("====/receivefl started====");
		
		
		res.setContentType("text/json;charset=UTF-8");
	    PrintWriter out = res.getWriter();
	    
	    
	    System.out.println("====received====");
		JSONParser parser = new JSONParser(); 
		Object obj = null;
		System.out.println("input is  " +input );
		try {
			obj = parser.parse(input);
			JSONObject flObject = (JSONObject) obj; 
			
			System.out.println(obj);
			
			System.out.println(flObject);
			
			flJsonList.put((String) flObject.get("forkliftid"), flObject);
		} catch (ParseException e) {
			e.printStackTrace();           
		} 

	    //flJsonList.put("forkLocX", "jmj"); 
	    
		JSONArray ja = new JSONArray();
	    
	    JSONObject jo = new JSONObject();
        for( Map.Entry<String, Object> entry : flJsonList.entrySet() ) {
            String key = entry.getKey();
            Object value = entry.getValue();
            jo.put(key, value);
        }

	    ja.add(jo);
		
		
	    out.print(ja.toJSONString());
	    out.close();
	    */
	    
	    
	    
	    

//		System.out.println("input type : " + input.getClass().getName());
//		System.out.println("obj type : " + obj.getClass().getName());
//		System.out.println("flJsonList type : " + flJsonList.getClass().getName());
//		////////////////////////////////////////////////
//		JSONArray ja = new JSONArray(); 
//		for(ForkLift f:flJsonList) {
//			flJsonList.put("forkStatus", f.getStatus());
//			flJsonList.put("forkLocX", f.getLocX());
//			flJsonList.put("forkLocY", f.getLocY());
//			flJsonList.put("forkBattery", f.getBattery());
//			flJsonList.put("forkTemperature", f.getTemperature());
//			ja.put(flJsonList);
//		}
//		res.setContentType("text/html; charset=utf-8");
//		PrintWriter out;
//		try {
//			out = res.getWriter();
//			out.print(ja.toString());
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		///////////////////////////////////////////////
		
		
		/*
		JSONArray ja = new JSONArray();
		
		JSONObject jo = new JSONObject();
		jo.put("name", "obama");
		ja.add(jo);
		
		
		*/
		
	}
	    
}
