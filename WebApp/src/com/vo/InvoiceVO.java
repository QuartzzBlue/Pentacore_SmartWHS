package com.vo;

import java.sql.Date;

public class InvoiceVO {
	int invoiceid;
	String itemid;
	String itemname;
	String wareid;
	String warename;
	String invoicestat;
	int invoiceqty;
	String empno;
	String empname;
	Date invoicedate;
	
	
	public InvoiceVO() {
		super();
	}
	// Database 넣을 때
	public InvoiceVO(String itemid, String itemname, String wareid, String warename, String invoicestat, int invoiceqty,
			String empno, String empname) {
		super();
		this.itemid = itemid;
		this.itemname = itemname;
		this.wareid = wareid;
		this.warename = warename;
		this.invoicestat = invoicestat;
		this.invoiceqty = invoiceqty;
		this.empno = empno;
		this.empname = empname;
	}
	// Database에서 꺼낼 때

	public InvoiceVO(int invoiceid, String itemid, String itemname, String wareid, String warename, String invoicestat,
			int invoiceqty, String empno, String empname, Date invoicedate) {
		super();
		this.invoiceid = invoiceid;
		this.itemid = itemid;
		this.itemname = itemname;
		this.wareid = wareid;
		this.warename = warename;
		this.invoicestat = invoicestat;
		this.invoiceqty = invoiceqty;
		this.empno = empno;
		this.empname = empname;
		this.invoicedate = invoicedate;
	}
	
	public int getInvoiceid() {
		return invoiceid;
	}
	public void setInvoiceid(int invoiceid) {
		this.invoiceid = invoiceid;
	}
	public String getItemid() {
		return itemid;
	}
	public void setItemid(String itemid) {
		this.itemid = itemid;
	}
	public String getItemname() {
		return itemname;
	}
	public void setItemname(String itemname) {
		this.itemname = itemname;
	}
	public String getWareid() {
		return wareid;
	}
	public void setWareid(String wareid) {
		this.wareid = wareid;
	}
	public String getWarename() {
		return warename;
	}
	public void setWarename(String warename) {
		this.warename = warename;
	}
	public String getInvoicestat() {
		return invoicestat;
	}
	public void setInvoicestat(String invoicestat) {
		this.invoicestat = invoicestat;
	}
	public int getInvoiceqty() {
		return invoiceqty;
	}
	public void setInvoiceqty(int invoiceqty) {
		this.invoiceqty = invoiceqty;
	}
	public String getEmpno() {
		return empno;
	}
	public void setEmpno(String empno) {
		this.empno = empno;
	}
	public String getEmpname() {
		return empname;
	}
	public void setEmpname(String empname) {
		this.empname = empname;
	}
	public Date getInvoicedate() {
		return invoicedate;
	}
	public void setInvoicedate(Date invoicedate) {
		this.invoicedate = invoicedate;
	}
	@Override
	public String toString() {
		return "InvoiceVO [invoiceid=" + invoiceid + ", itemid=" + itemid + ", itemname=" + itemname + ", wareid="
				+ wareid + ", warename=" + warename + ", invoicestat=" + invoicestat + ", invoiceqty=" + invoiceqty
				+ ", empno=" + empno + ", empname=" + empname + ", invoicedate=" + invoicedate + "]";
	}
	
	
	
}
