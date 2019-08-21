package org.grayleaves.sweater;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.grayleaves.utility.Clock;

class StatusResponse {

	protected static Logger logger = LogManager.getLogger(StatusResponse.class);
	public static final String NAME = "sweater-sample";
	public static final String NORMAL = "normal";
	public static final String EXCEPTION = "exception: oops!";
	private static String RESPONSE = NORMAL;
	protected static int DELAY = 0;
	protected static boolean HANG = false;
	public static boolean THROW_EXCEPTIONS;
	private long elapsedTime;
	private Clock clock;

	public static void forceDelay(int delay) {
		DELAY = delay;
	}

	public static void hang(boolean hang) {
		logger.info("request to hang:  " + hang);
		HANG = hang;
		if (hang) {
			logger.error("about to hang forever");
			forceDelay(Integer.MAX_VALUE);
		} else {
			logger.info("returning to no delay");
			forceDelay(0);
		}
	}

	public static void throwExceptions(boolean exceptions) {
		THROW_EXCEPTIONS = exceptions;
		if (exceptions) {
			logger.error("about to throw an exception");
			RESPONSE = EXCEPTION;
		} else {
			logger.info("returning normal response");
			RESPONSE = NORMAL;
		}
	}

	public StatusResponse() {
		clock = new Clock();
		clock.start();
	}

	public void delay() {
		if (DELAY > 0) {
			logger.info("about to sleep for (milliseconds): " + DELAY);
			Clock.sleep(DELAY);
		}
		clock.stop();
		setElapsedTime(clock.getElapsedTime());
	}

	public String getName() {
		return NAME;
	}

	/**
	 * MOXy apparently requires Javabean accessor methods
	 * 
	 * @param name
	 *            (unused)
	 */
	public void setName(String name) {
	}

	public int getDelay() {
		return DELAY;
	}

	public void setDelay(int delay) {
	}

	public long getElapsedTime() {
		return elapsedTime;
	}

	public void setElapsedTime(long milliseconds) {
		elapsedTime = milliseconds;
	}

	public String getResponse() {
		return RESPONSE;
	}

	public void setResponse(String response) {
	}
}
