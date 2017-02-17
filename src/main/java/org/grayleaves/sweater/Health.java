package org.grayleaves.sweater;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@Path("/v1.1/health")
public class Health {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public HealthResponse knit(@PathParam("stitches") int stitches) {
		HealthResponse healthResponse = new HealthResponse(); 
		return healthResponse;
	}


}
