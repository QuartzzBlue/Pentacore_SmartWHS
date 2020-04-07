package com.controller;


import java.io.IOException;
import java.util.HashMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.servlet.ModelAndView;

import com.frame.Service;
import com.vo.ForkliftVO;

@Controller
public class ForkliftController {
	@Resource(name = "flservice")
	Service<ForkliftVO> flservice;
	
	static HashMap<String, Object> flJsonList = new HashMap<>();
	
	
//	@RequestMapping("/fllist.pc")
//	public ModelAndView fllist(ModelAndView mv, HttpServletRequest request, HttpServletResponse response) {
//
//		
//		return mv;
//	}
	
	
	@ResponseBody
	@RequestMapping("/receivefl.pc")
	public void receivefl(@RequestBody String input) { 
		
		JSONParser parser = new JSONParser(); 
		Object obj;
		try {
			obj = parser.parse(input);
			JSONObject flObject = (JSONObject) obj; 
			
			
			flJsonList.put((String) flObject.get("forkliftid"), flObject);
		} catch (ParseException e) {
			e.printStackTrace();
		} 

		System.out.println(flJsonList.toString());
		
	}
	
	
}
