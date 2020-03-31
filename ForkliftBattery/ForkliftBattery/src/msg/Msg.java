package msg;

import java.io.Serializable;

public class Msg implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	String srcIP;
	String srcID;
	String dstnIP;
	String dstnID;
	
	Task task;
	ForkLift forkLift;
	
	public Msg() {

	}
	
	public Msg(String srcID, String dstnID) {
		this.srcID = srcID;
		this.dstnID = dstnID;
	}

	public Msg(String srcIP, String srcID, String dstnIP, String dstnID) {
		this.srcIP = srcIP;
		this.srcID = srcID;
		this.dstnIP = dstnIP;
		this.dstnID = dstnID;

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

	public Task getTask() {
		return task;
	}

	public void setTask(int io, int qty, int locX, int locY) {
		if(this.task==null) {
			task = new Task(io,qty,locX,locY);
		}else {
			task.setIo(io);
			task.setLocX(locX);
			task.setLocY(locY);
			task.setQty(qty);
		}
	}

	public ForkLift getForkLift() {
		return forkLift;
	}

	public void setForkLift(int locX, int locY,int battery,int status) {
		if(this.forkLift==null) {
			forkLift = new ForkLift(locX, locY,battery, status);
		}else {
			forkLift.setStatus(status);
			forkLift.setBattery(battery);
			forkLift.setLocX(locX);
			forkLift.setLocY(locY);
		}
	}
}


