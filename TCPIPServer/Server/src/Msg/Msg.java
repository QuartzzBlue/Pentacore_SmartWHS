package Msg;

import java.io.Serializable;

public class Msg implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	String srcIP;
	String srcID;
	String dstnIP;
	String dstnID;
	String content;
	
	
	public Msg() {
		super();
	}


	public Msg(String srcID, String dstnID, String content) {
	
		this.srcID = srcID;
		this.dstnID = dstnID;
		this.content = content;
	}


	public Msg(String srcIP, String srcID, String dstnIP, String dstnID, String content) {

		this.srcIP = srcIP;
		this.srcID = srcID;
		this.dstnIP = dstnIP;
		this.dstnID = dstnID;
		this.content = content;
	}


	public String getSrcIP() {
		return srcIP;
	}


	public void setSrcIP(String srcIP) {
		this.srcIP = srcIP;
	}


	public String getSrcID() {
		return srcID;
	}


	public void setSrcID(String srcID) {
		this.srcID = srcID;
	}


	public String getDstnIP() {
		return dstnIP;
	}


	public void setDstnIP(String dstnIP) {
		this.dstnIP = dstnIP;
	}


	public String getDstnID() {
		return dstnID;
	}


	public void setDstnID(String dstnID) {
		this.dstnID = dstnID;
	}


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}
	
	
	


}
