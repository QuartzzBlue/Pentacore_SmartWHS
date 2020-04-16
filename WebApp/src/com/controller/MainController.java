package com.controller;

import java.util.ArrayList;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.frame.Service;
import com.vo.ForkliftVO;


@Controller
public class MainController {

	@Resource(name = "flservice")
	Service<ForkliftVO> flservice;
	
	@RequestMapping("/main.pc")
	public ModelAndView main() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("main");
		return mv;
	}
	
	@RequestMapping("/itpage.pc")
	public ModelAndView pdpage(ModelAndView mv) {

		mv.addObject("center", "itpage");
		mv.setViewName("main");

		return mv;
	}
	
	@RequestMapping("/flpage.pc")
	public ModelAndView flpage(ModelAndView mv) {
		
		try {
			ForkliftVO fl = flservice.select(new ForkliftVO("Forklift01", null, null, null, null, 0));
			System.out.println("**" + fl);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		
		
		/* Forklift list ¶ç¿ì±â */
		ArrayList<ForkliftVO> fllist = null;
		try {
			fllist = flservice.selectAll(new ForkliftVO());
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(fllist.toString());
		mv.addObject("fllist", fllist);
		
		/* center ºÎºÐ¿¡ flpage ¶ç¿ì±â */
		mv.addObject("center", "flpage");
		mv.setViewName("main");

		return mv;
	}
	
	@RequestMapping("/solpage.pc")
	public ModelAndView sltpage(ModelAndView mv) {

		mv.addObject("center", "solpage");
		mv.setViewName("main");

		return mv;
	}
	
}
