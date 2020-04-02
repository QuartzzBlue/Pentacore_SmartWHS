package com.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.frame.Service;
import com.vo.ItemVO;

@Controller
public class ItemController {
	@Resource(name = "itservice")
	Service<ItemVO> itservice;
	
	@RequestMapping("/itemregister.pc")
	public ModelAndView itemregister(ModelAndView mv, HttpServletRequest request, HttpServletResponse response){
		ItemVO newItem = new ItemVO();
		newItem.setItemid(request.getParameter("itemid"));
		newItem.setItemname(request.getParameter("itemname"));
		newItem.setItemcate(request.getParameter("itemcate"));
		newItem.setItemprice(Integer.parseInt(request.getParameter("itemprice")));
		newItem.setItemweightpb(Double.parseDouble(request.getParameter("itemweightpb")));
		newItem.setItemqtypb(Integer.parseInt(request.getParameter("itemqtypb")));
		newItem.setWareid(request.getParameter("wareid"));
		newItem.setWarename(request.getParameter("warename"));
		
		itservice.insert(newItem);
		
		return mv;
	}
}
