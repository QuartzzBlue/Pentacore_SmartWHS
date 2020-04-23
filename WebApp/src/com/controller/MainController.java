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
	
	//employee 테이블 서비스
	
	@RequestMapping("/loginpage.pc") 
	public ModelAndView loginpage(ModelAndView mv, HttpServletRequest request) {
		if(request.getParameter("error") != null) { //아이디 또는 비밀번호 문제 분기
			mv.addObject("error", "아이디나 비밀번호가 맞지 않습니다.");
		}
		
		mv.setViewName("login");
		return mv;
	}
	
	@RequestMapping("/login.pc") 
	public String login(HttpServletRequest request, ModelAndView mv) {//나중에 VO로 바꾸기
		String empno = request.getParameter("empno");
		String emppwd = request.getParameter("emppwd");
		String nextJsp = null;
		if(empno.equals("emp1010") && emppwd.equals("101010")) {	//  DB에서 정보 가져와서 비교
			String empname = "홍길동";	//empname 같이 넘겨줌
			nextJsp = "redirect:main.pc?id="+empno+"&name="+empname;
			
		}else {
			nextJsp = "redirect:loginpage.pc?error="+1;
		}
		return nextJsp;
	}
	
	@RequestMapping("/main.pc")
	public ModelAndView main(ModelAndView mv, HttpServletRequest request) {	
		if(request.getParameter("id") != null) {
			String empno = request.getParameter("id");
			String empname = request.getParameter("name");
			mv.addObject("id", empno);
			mv.addObject("name", empname);
		}
		mv.setViewName("main");
		return mv;
	}
	
	@RequestMapping("/itpage.pc")
	public ModelAndView itpage(ModelAndView mv) {

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
		
		
		/* Forklift list 띄우기 */
		ArrayList<ForkliftVO> fllist = null;
		try {
			fllist = flservice.selectAll(new ForkliftVO());
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(fllist.toString());
		mv.addObject("fllist", fllist);
		
		/* center 부분에 flpage 띄우기 */
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
