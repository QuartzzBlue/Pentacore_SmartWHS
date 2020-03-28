package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class MainController {

	@RequestMapping("/main.hc")
	public ModelAndView main() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("main");
		return mv;
	}
	
	@RequestMapping("/pdpage.hc")
	public ModelAndView pdpage(ModelAndView mv) {

		mv.addObject("center", "pdpage");
		mv.setViewName("main");

		return mv;
	}
	
	@RequestMapping("/flpage.hc")
	public ModelAndView flpage(ModelAndView mv) {

		mv.addObject("center", "flpage");
		mv.setViewName("main");

		return mv;
	}
	
	@RequestMapping("/solpage.hc")
	public ModelAndView sltpage(ModelAndView mv) {

		mv.addObject("center", "solpage");
		mv.setViewName("main");

		return mv;
	}
	
}
