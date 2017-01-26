package org.grayleaves.sweater;

import org.grayleaves.utility.Clock;

class StatusResponse {
		public static final String NAME = "sweater-sample"; 
		public static final String NORMAL = "normal"; 
		public static final String EXCEPTION = "exception: oops!"; 
		private static String RESPONSE = NORMAL; 
		protected static int DELAY = 0;
		protected static boolean HANG = false;
		public static boolean THROW_EXCEPTIONS;
		
		public static void forceDelay(int delay) {
			DELAY = delay; 
		}
		public static void hang(boolean hang) {
			HANG = hang;
			if (hang) {
				forceDelay(Integer.MAX_VALUE); 
			} else {
				forceDelay(0); 
			}
		}
		public static void throwExceptions(boolean exceptions) {
			THROW_EXCEPTIONS = exceptions; 
			if (exceptions) {
				RESPONSE = EXCEPTION; 
			} else {
				RESPONSE = NORMAL; 
			}
		}

		private long elapsedTime;
		private Clock clock;
		
		public StatusResponse() {
			clock = new Clock(); 
			clock.start(); 
		}
		public void delay() {
			if (DELAY > 0) {
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
		 * @param name (unused)
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
