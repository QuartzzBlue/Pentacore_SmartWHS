package com.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.frame.Service;
import com.vo.InvoiceVO;
import com.vo.InvoicedetailVO;
import com.vo.ItemVO;

@Controller
@SessionAttributes("dtllist")	//model.addAttribute()를 사용해 객체를 저장할 경우 세션에 저장됨 (SessionStatus객체의 setComplete() 사용해서 지울 때까지)
public class ItemController {
	@Resource(name = "itservice")
	Service<ItemVO> itservice;
	
	@Resource(name = "invservice")
	Service<InvoiceVO> invservice;
	
	@RequestMapping("/itemregister.pc")
	public ModelAndView itemregister(ModelAndView mv, ItemVO newItem){

		
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
	public void itemsearch(HttpServletResponse rs, ItemVO iv) {


		ArrayList<ItemVO> itemList = null;
		try {
			itemList =  itservice.selectAll(iv);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("--"+itemList.toString());
		
		JSONArray ja = new JSONArray(); 
		for(ItemVO i:itemList) {
			JSONObject json = new JSONObject();
			json.put("itemid", i.getItemid());
			json.put("itemname", i.getItemname());
			json.put("itemcate", i.getItemcate());
			json.put("itemprice", i.getItemprice());
			json.put("itemweigthpb", i.getItemweightpb());
			json.put("itemqtypb", i.getItemqtypb());
			json.put("wareid", i.getWareid());
			json.put("warename", i.getWarename());
			json.put("itemloc", i.getItemloc());
			json.put("itemstock", i.getItemstock());
			ja.add(json);
		}
		rs.setContentType("text/html; charset=utf-8");
		PrintWriter out;
		try {
			out = rs.getWriter();
			out.print(ja.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
//	@RequestMapping(value = "/invoicedtllist.pc")
//	public ModelAndView invoicedtllist(ModelAndView mv, Model m, @ModelAttribute("dtllist") ArrayList<InvoicedetailVO> dtllist){
//		
//	 	
//	 	
////	 	JSONObject dtllist = (JSONObject) session.getAttribute("dtllist");
//		
//		return mv;
//	}
	
	@RequestMapping("/invoiceregister.pc")
	public ModelAndView invoiceregister(ModelAndView mv, InvoiceVO newInvoice){
		
		System.out.println("&&&&&&&&&&&&&" + newInvoice.toString());
		
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
	public ModelAndView invoicesearch(ModelAndView mv, InvoiceVO ivv) {
		
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
