package org.grayleaves.sweater;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@Path("/v1.1/color")
public class Color {

	@GET
	@Path("{color}")
	@Produces(MediaType.APPLICATION_JSON)
	public ControlResponse knit(@PathParam("color") String color) {
		ControlResponse response = new ControlResponse(); 
		response.setColor(color);
		System.err.println("Color requested: "+color);
		return  response; 

	}


}
