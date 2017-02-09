package org.grayleaves.sweater;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;



	@ApplicationPath("/api/*")
	public class ApiV1App extends Application {
	    public ApiV1App() {
	    	System.out.println("liberty reached");
	    	configure();
	    }
	    public static void configure() {
	    	String flag = System.getenv("USE_WEB_YARN_SERVICE");
	    	System.out.println("flag: "+flag);
	    	if ((flag != null) && (flag.equalsIgnoreCase("true"))) {
	    		KnitStatusResponse.setYarnService(new WebYarnService()); 
	    		System.out.println("configuring WebYarnService");
	    	} else {
	    		KnitStatusResponse.setYarnService(new SimpleYarnService(YarnEnum.GRAY, 10)); 
	    	}
	    }
	    
}
