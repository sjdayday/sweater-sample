package org.grayleaves.sweater;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Path("/status")
public class Status {

    protected static Logger logger = LogManager.getLogger(Status.class);

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public StatusResponse statusMessage() {
        StatusResponse statusResponse = new StatusResponse();
        logger.info("status response requested");
        statusResponse.delay();
        return statusResponse;
    }

}
