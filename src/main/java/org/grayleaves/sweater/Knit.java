package org.grayleaves.sweater;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@Path("/v1.1/knit")
public class Knit {

	@GET
	@Path("{stitches}")
	@Produces(MediaType.APPLICATION_JSON)
	public KnitStatusResponse knit(@PathParam("stitches") int stitches) {
		KnitStatusResponse knitResponse = new KnitStatusResponse(); 
		knitResponse.knit(stitches);
		System.out.println("Status elapsed time: "+knitResponse.getElapsedTime()+"; response: "+knitResponse.getResponse()+"; knitting: "+knitResponse.getKnitResponse());
		return knitResponse;
	}


}
