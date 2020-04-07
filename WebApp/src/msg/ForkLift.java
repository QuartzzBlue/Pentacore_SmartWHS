package msg;

import java.io.Serializable;

public class ForkLift implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	int locX;
	int locY;
	int battery;
	int status;
	String flname;

	public ForkLift() {}
	
	public ForkLift(int locX, int locY, int battery, int status) {
		this.locX = locX;
		this.locY = locY;
		this.battery = battery;
		this.status = status;
	}
	public int getLocX() {
		return locX;
	}
	public void setLocX(int locX) {
		this.locX = locX;
	}
	public int getLocY() {
		return locY;
	}
	public void setLocY(int locY) {
		this.locY = locY;
	}
	public int getBattery() {
		return battery;
	}
	public void setBattery(int battery) {
		this.battery = battery;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getFlname() {
		return flname;
	}
	public void setFlname(String flname) {
		this.flname = flname;
	}
	

	

}
