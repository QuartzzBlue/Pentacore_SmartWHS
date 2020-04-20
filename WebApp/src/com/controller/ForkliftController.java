package com.controller;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.Response;

import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONArray;
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
	
	static HashMap<String, Object> flJsonList = new HashMap<>();

	
	
	@RequestMapping("/..pc")
	@ResponseBody
	public void receivefl(HttpServletResponse res, @RequestBody String input) throws IOException { 
		System.out.println("/receivefl started");
		
		res.setContentType("text/json;charset=UTF-8");
	    PrintWriter out = res.getWriter();
		
	    System.out.println("received");
		JSONParser parser = new JSONParser(); 
		Object obj;
		
		System.out.println("input is  " +input );
		System.out.println();
		try {
			obj = parser.parse(input);
			JSONObject flObject = (JSONObject) obj; 
			
			
			System.out.println(obj);
			System.out.println(flObject);
			
			flJsonList.put((String) flObject.get("forkliftid"), flObject);
		} catch (ParseException e) {
			e.printStackTrace();           
		} 

		System.out.println("dd" + flJsonList.toString());
		
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
//		////////////////////////////////////////////////
		
		out.print("sent");
	    out.close();
	}
}
