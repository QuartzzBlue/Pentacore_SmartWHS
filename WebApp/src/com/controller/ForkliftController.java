package com.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.frame.Service;
import com.vo.ForkliftVO;

@Controller
public class ForkliftController {
	@Resource(name = "flservice")
	Service<ForkliftVO> flservice;
	
	@RequestMapping("/fllist.pc")
	public ModelAndView fllist(ModelAndView mv, HttpServletRequest request, HttpServletResponse response) {
		
		
		return mv;
		
	}
	
}
