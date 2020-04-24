package com.controller;

import java.util.ArrayList;

import javax.annotation.Resource;

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
}
