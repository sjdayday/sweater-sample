package org.grayleaves.sweater;

public class HealthResponse {

	public static String INSTANCE_KEY = "CF_INSTANCE_INDEX";
	public static String INSTANCE_VALUE = "-1";
	private String status = "UP";

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
	}

	public String getInstance() {
		return INSTANCE_VALUE;
	}

	public void setInstance(String instance) {
	}
	
}
