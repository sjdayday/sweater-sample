package org.grayleaves.sweater;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;



	@ApplicationPath("/api/*")
	public class ApiV1App extends Application {
	    public ApiV1App() {
	    	System.out.println("liberty reached");
//	        packages("org.grayleaves.sweater");
	    }
	    public static void configure() {
			KnitStatusResponse.setYarnService(new SimpleYarnService(YarnEnum.GRAY, 10)); 
	    }
	    
}
