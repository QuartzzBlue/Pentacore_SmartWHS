package msg;

import java.io.Serializable;

class Task implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	int io;
	int qty;
	int locX;
	int locY;
	
	public Task() {}

	public Task(int io, int qty, int locX, int locY) {
		this.io = io;
		this.qty = qty;
		this.locX = locX;
		this.locY = locY;
	}

	public int getIo() {
		return io;
	}

	public void setIo(int io) {
		this.io = io;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
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

} 

