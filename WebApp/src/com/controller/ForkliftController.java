package com.controller;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

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
	
	static HashMap<String, Object> flJsonList = new HashMap<>();
	static JSONObject jo = new JSONObject();
	static HashMap<String, Long> fldist = new HashMap<>();
	
	
	@ResponseBody 
	@RequestMapping("/distance.pc")
	public void distancefl(HttpServletResponse res) throws IOException {
		setDrivenDist();
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
	
	
	@ResponseBody //input 에 Requestbody 지워준다
	@RequestMapping("/sendfl.pc")
	public void sendfl(HttpServletResponse res) throws IOException {

		PrintWriter out = res.getWriter();
	    out.print(jo.toJSONString());
	    out.close();
	}
	
	@RequestMapping("/getDist.pc")
	public @ResponseBody HashMap<String, Long> getDist() throws IOException {

		return fldist;
	}
	
	

//	@ResponseBody //input 에 Requestbody 지워준다
	@RequestMapping("/receivefl.pc")
	public void receivefl(HttpServletResponse res, @RequestBody String input) throws IOException {
		JSONParser parser = new JSONParser();
		try {
			JSONObject obj = (JSONObject)parser.parse(input);
			System.out.println("ojb : "+obj.toJSONString());
			
			String id = (String)obj.get("forkliftid");
			long dist = (long) obj.get("distanceDriven");
			jo.put(id, input);
			ForkliftVO newfl = new ForkliftVO();
			newfl.setForkid(id);
			newfl.setForkdist(dist);
			try {
				flservice.update(newfl);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			//System.out.println("dist : " + dist);
			if(fldist.containsKey(id)) {
				fldist.put(id, fldist.get(id)+dist);
			}else {
				fldist.put(id, dist);
			}
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return;

	}
	
	public void setDrivenDist() throws IOException {
		ArrayList<ForkliftVO> fllist = null;
		
		try {
			fllist = flservice.selectAll(new ForkliftVO());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		for(ForkliftVO fl : fllist) {
			fldist.put(fl.getForkid(), fl.getForkdist());
		}
		
	}
	
}
