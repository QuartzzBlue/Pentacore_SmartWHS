package com.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.json.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.frame.Service;
import com.vo.EmployeeVO;

@Controller
public class EmployeeController {
	
	@Resource(name = "empservice")
	Service<EmployeeVO> empservice;
	
	
	
	@RequestMapping("getemplist.pc")
	public @ResponseBody ArrayList<EmployeeVO> emppage(ModelAndView mv) {
		ArrayList<EmployeeVO> emplist = null;
		try {
			emplist = empservice.selectAll(new EmployeeVO());
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("emp : " + emplist.toString());
		
		return emplist;
	}
	
	@RequestMapping("getempnumbywh.pc")
	public @ResponseBody String getempnumbywh(ModelAndView mv) {
		ArrayList<EmployeeVO> emplist = null;
		try {
			emplist = empservice.selectAll(new EmployeeVO());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		HashMap<String, Integer> empnum = new HashMap<String, Integer>();
		System.out.println(emplist.size());
		
		for(EmployeeVO emp : emplist) {
			String wareid = emp.getWareid();
			if(empnum.containsKey(wareid)) {
				int temp = empnum.get(wareid);
				empnum.put(wareid, ++temp);
			}else {
				empnum.put(wareid, 1);
			}
		}
		
		
		JSONArray ja = new JSONArray();
		for(Map.Entry<String, Integer> entry : empnum.entrySet()) {
			JSONObject jo = new JSONObject();
			jo.put("label", entry.getKey());
			jo.put("value", entry.getValue());
			ja.put(jo);
		}
		System.out.println(ja.toString());
		return ja.toString();
	}
}
