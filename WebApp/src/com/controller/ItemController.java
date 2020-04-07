package com.controller;

import java.util.ArrayList;
import java.util.Date;

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
	public ModelAndView itemregister(ModelAndView mv, ItemVO newItem){
		
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


		ArrayList<ItemVO> itemList = null;
		try {
			itemList =  itservice.selectAll(iv);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("--"+itemList.toString());
		
		String itTableHeader = "<thead>\n <tr>\n" + 
				"											<th>Item ID</th>\n" + 
				"											<th>Item Name</th>\n" + 
				"											<th>Category</th>\n" + 
				"											<th>Price (KRW/box)</th>\n" + 
				"											<th>Weight (KG/box)</th>\n" + 
				"											<th>Qty (per box)</th>\n" + 
				"											<th>Warehouse ID</th>\n" + 
				"											<th>Warehouse Name</th>\n" + 
				"											<th>Location</th>\n" + 
				"											<th>Stock</th>\n" + 
				"										</tr>\n </thead>";
		mv.addObject("itTableHeader", itTableHeader);
		mv.addObject("itemList", itemList);
		mv.addObject("center", "itpage");
		mv.setViewName("main");
		
		return mv;
		
		
	}
	
	@RequestMapping("/invoiceregister.pc")
	public ModelAndView invoiceregister(ModelAndView mv, InvoiceVO newInvoice){
		
		System.out.println("&&&&&&&&&&&&&" + newInvoice.toString());
		
//		InvoiceVO newInvoice = new InvoiceVO();
//		newInvoice.setItemid(request.getParameter("val-itemid"));
//		newInvoice.setItemname(request.getParameter("val-itemname"));
//		newInvoice.setWareid(request.getParameter("val-wareid"));
//		newInvoice.setInvoiceqty(Integer.parseInt(request.getParameter("val-invoiceqty")));
//		newInvoice.setInvoicestat(request.getParameter("val-invoicestat"));
		
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
	public ModelAndView invoicesearch(ModelAndView mv, InvoiceVO ivv, HttpServletRequest request, HttpServletResponse response) {
		
		String start = request.getParameter("startdate");
		String end = request.getParameter("enddate");
		
		System.out.println("##" + start + "##" + end);
		System.out.println("!!!!"+ivv.toString());
		ArrayList<InvoiceVO> invoiceList = null;
		try {
			invoiceList = invservice.selectAll(ivv);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("@@@" + invoiceList.toString());
		
		String ivTableHeader = "<thead>\r\n" + 
				"										<tr>\r\n" + 
				"											<th>Item ID</th>\r\n" + 
				"											<th>Item Name</th>\r\n" + 
				"											<th>Warehouse Name</th>\r\n" + 
				"											<th>Status</th>\r\n" + 
				"											<th>Qty</th>\r\n" + 
				"											<th>Date</th>\r\n" + 
				"										</tr>\r\n" + 
				"									</thead>";
		mv.addObject("ivTableHeader", ivTableHeader);
		mv.addObject("invoiceList", invoiceList);
		mv.addObject("center", "itpage");
		mv.setViewName("main");
		return mv;
		
		
	}
}
