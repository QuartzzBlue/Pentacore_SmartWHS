package com.controller;

import java.util.ArrayList;

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
	public ModelAndView itemregister(ModelAndView mv, HttpServletRequest request, HttpServletResponse response, ItemVO newItem){
		
//		ItemVO newItem = new ItemVO();
//		newItem.setItemid(request.getParameter("val-itemid"));
//		newItem.setItemname(request.getParameter("val-itemname"));
//		newItem.setItemcate(request.getParameter("val-itemcate"));
//		newItem.setItemprice(Integer.parseInt(request.getParameter("val-itemprice")));
//		newItem.setItemweightpb(Double.parseDouble(request.getParameter("val-itemweightpb")));
//		newItem.setItemqtypb(Integer.parseInt(request.getParameter("val-itemqtypb")));
//		newItem.setWareid(request.getParameter("val-wareid"));
//		newItem.setWarename(request.getParameter("val-warename"));
		
		System.out.println("**"+newItem.toString());
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
	public ModelAndView itemsearch(ModelAndView mv, ItemVO iv) {
//		ItemVO iv = new ItemVO();
		
		ArrayList<ItemVO> itemList = null;
		try {
			itemList =  itservice.selectAll(iv);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		mv.addObject("itemList", itemList);
		mv.addObject("center", "itpage");
		mv.setViewName("main");
		
		return mv;
		
		
	}
	
	@RequestMapping("/invoiceregister.pc")
	public ModelAndView invoiceregister(ModelAndView mv, HttpServletRequest request, HttpServletResponse response){
		InvoiceVO newInvoice = new InvoiceVO();
		newInvoice.setItemid(request.getParameter("val-itemid"));
		newInvoice.setItemname(request.getParameter("val-itemname"));
		newInvoice.setWareid(request.getParameter("val-wareid"));
		newInvoice.setInvoiceqty(Integer.parseInt(request.getParameter("val-invoiceqty")));
		newInvoice.setInvoicestat(request.getParameter("val-invoicestat"));
		
		try {
			invservice.insert(newInvoice);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		mv.addObject("center", "itpage");
		mv.setViewName("main");
		
		return mv;
	}
	
	@RequestMapping("/invoicesearch.pc")
	public ModelAndView invoicesearch(ModelAndView mv) {
		
		
		return mv;
		
		
	}
}
