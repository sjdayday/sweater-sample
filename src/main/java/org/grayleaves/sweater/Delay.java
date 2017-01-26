package org.grayleaves.sweater;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@Path("/delay")
public class Delay {

	@GET
	@Path("{delay}")
	@Produces(MediaType.APPLICATION_JSON)
	public ControlResponse control(@PathParam("delay") int delay) {
		ControlResponse response = new ControlResponse(); 
		response.setGlobalDelay(delay);
		System.err.println("Delay requested: "+delay);
		return  response; 
	}
	
}
