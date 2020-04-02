package com.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.frame.Service;
import com.vo.InvoiceVO;
import com.vo.ItemVO;

@Controller
public class ItemController {
	@Resource(name = "itservice")
	Service<ItemVO> itservice;
	
	@Resource(name = "invservice")
	Service<InvoiceVO> invservice;
	
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
		
		try {
			itservice.insert(newItem);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		mv.addObject("center", "itpage");
		mv.setViewName("main");

		return mv;
	}
	
	@RequestMapping("/itemsearch.pc")
	public ModelAndView itemsearch(ModelAndView mv) {
		
		
		return mv;
		
		
	}
	
	@RequestMapping("/invoiceregister.pc")
	public ModelAndView invoiceregister(ModelAndView mv, HttpServletRequest request, HttpServletResponse response){
		InvoiceVO newInvoice = new InvoiceVO();
		newInvoice.setItemid(request.getParameter("itemid"));
		newInvoice.setItemname(request.getParameter("itemname"));
		newInvoice.setWareid(request.getParameter("wareid"));
		newInvoice.setInvoiceqty(Integer.parseInt(request.getParameter("invoiceqty")));
		newInvoice.setInvoicestat(request.getParameter("invoicestat"));
		
		try {
			invservice.insert(newInvoice);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		mv.addObject("center", "itpage");
		mv.setViewName("main");
		
		return mv;
	}
}
