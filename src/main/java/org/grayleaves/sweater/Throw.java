package org.grayleaves.sweater;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@Path("/throw")
public class Throw {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public ControlResponse control() {
		ControlResponse response = new ControlResponse(); 
		response.setThrowException(true);
		System.err.println("Throw exceptions requested.");
		return  response; 
	}
	
}
