package org.grayleaves.sweater;

public class ControlResponse {

	private String command = ""; 
	private int globalDelay;
//	private boolean hang; 
	
	public ControlResponse() {
	}
	
	public void setGlobalDelay(int delay) {
		StatusResponse.forceDelay(delay); 
		setCommand("setGlobalDelay");
		globalDelay = delay; 
	}
	public int getGlobalDelay() {
		return StatusResponse.DELAY;
	}
	public String getCommand() {
		return command;
	}
	public void setCommand(String command) {
		this.command = command;
	}
	public void setHang(boolean hang) {
		StatusResponse.hang(hang);
		if (hang) {
			setCommand("setHang");
		} else {
			StatusResponse.forceDelay(globalDelay); 
		}
//		this.hang = hang;
	}
	public boolean isHang() {
		return StatusResponse.HANG; 
	}

	public boolean isThrowException() {
		return StatusResponse.THROW_EXCEPTIONS;
	}

	public void setThrowException(boolean exceptions) {
		StatusResponse.throwExceptions(exceptions); 
		if (exceptions) {
			setCommand("setThrowExceptions"); 
		}
	}

	public void setColor(String color) {
		KnitStatusResponse.forceColor(color); 
		setCommand("setColor"); 
	}
	public String getColor() {
		return KnitStatusResponse.COLOR.toString(); 
	}
}
