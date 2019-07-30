package org.grayleaves.sweater;

// simplified to bean to enable easy parsing by WebYarnService
public class YarnStatusResponse extends StatusResponse {

	private String yarnResponse = "";
	private int used;
	private String color;

	public YarnStatusResponse() {
		super(); 
	}
		
	public String getColor() {
		return color; 
	}
	public void setColor(String color) {
		this.color = color; 
	}
	public String getYarnResponse() {
		return yarnResponse;
	}

	public void setYarnResponse(String yarnResponse) {
		this.yarnResponse = yarnResponse;
	}

	public int getUsed() {
		return used;
	}

	public void setUsed(int used) {
		this.used = used;
	}
}
