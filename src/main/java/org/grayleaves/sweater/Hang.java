package org.grayleaves.sweater;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@Path("/hang")
public class Hang {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public ControlResponse control() {
		ControlResponse response = new ControlResponse(); 
		response.setHang(true);
		System.err.println("Hang requested.");
		return  response; 
	}
	
}
