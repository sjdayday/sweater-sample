package org.grayleaves.sweater;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ControlResponse {

	protected static Logger logger = LogManager.getLogger(ControlResponse.class);
	private String command = "";
	private int globalDelay;
	// private boolean hang;

	public ControlResponse() {
	}

	public void setGlobalDelay(int delay) {
		StatusResponse.forceDelay(delay);
		setCommand("setGlobalDelay");
		logger.info("setting delay for future status requests (milliseconds):  " + delay);
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
		logger.info("setting hang for future status requests:  " + hang);
		if (hang) {
			setCommand("setHang");
			logger.debug("future requests will hang");
		} else {
			StatusResponse.forceDelay(globalDelay);
			logger.debug("future requests will not hang, but will just delay as previously set.");
		}
		// this.hang = hang;
	}

	public boolean isHang() {
		return StatusResponse.HANG;
	}

	public boolean isThrowException() {
		return StatusResponse.THROW_EXCEPTIONS;
	}

	public void setThrowException(boolean exceptions) {
		StatusResponse.throwExceptions(exceptions);
		logger.info("setting exceptions for future status requests:  " + exceptions);
		if (exceptions) {
			setCommand("setThrowExceptions");
			logger.debug("future requests will throw exceptions");
		}
	}
}
