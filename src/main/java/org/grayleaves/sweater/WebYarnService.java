package org.grayleaves.sweater;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.ClientProperties;
import org.glassfish.jersey.logging.LoggingFeature;



public class WebYarnService implements YarnService {

	protected static final String YARN_SERVICE_URL = "YARN_SERVICE_URL";
	private static final int DEFAULT_TIMEOUT = 10000;
	public WebYarnService() {
		buildURL();
	}
	//  http://yarn.mybluemix.net/api/v1"
	private String targetURL = "no url provided"; 

	@Override
	public int getYarn(YarnEnum color, int yards) {
	  int available = 0; 
	  ClientConfig configuration = buildTimeoutConfiguration();
	  Client client = ClientBuilder.newClient(configuration);
//	  Client client = ClientBuilder.newClient( new ClientConfig().register( LoggingFeature.class ) );
	  WebTarget target = client.target(targetURL).path("color").path(color.toString()); 
	  Response response = target.request(MediaType.APPLICATION_JSON).get(Response.class); 
	  if (response.getStatus() == 200) {
		  target = client.target(targetURL).path("use").path(""+yards); 
		  response = target.request(MediaType.APPLICATION_JSON).get(Response.class); 
//		  System.out.println(response.toString());
		  YarnStatusResponse yarnResponse = response.readEntity(YarnStatusResponse.class);
//		  System.out.println(yarnResponse);
		  available = yarnResponse.getUsed(); 
	  }
	  else {
		  System.err.println("status: "+response.getStatus()+"\n"+response.toString());
	  }
	  return available;
	}
	private ClientConfig buildTimeoutConfiguration() {
		ClientConfig configuration = new ClientConfig().register( LoggingFeature.class );
		  configuration.property(ClientProperties.CONNECT_TIMEOUT, getTimeout("CONNECT_TIMEOUT"));
		  configuration.property(ClientProperties.READ_TIMEOUT, getTimeout("READ_TIMEOUT"));
		return configuration;
	}
	private int getTimeout(String timeout) {
		String timeoutValue = System.getenv(timeout);
		int time = DEFAULT_TIMEOUT; 
		if (timeoutValue != null) {
			try {
				time = Integer.parseInt(timeoutValue);
			} 
			catch (NumberFormatException e) {} 
		}
//		System.out.println(timeout+": "+time);
		return time;
	}
	private void buildURL() {
		String url = System.getenv(YARN_SERVICE_URL); 
		if (url != null) {
			targetURL = url;
		}
		System.out.println("WebYarnService target url: "+targetURL);
	}
	public String getTargetURL() {
		return targetURL;
	}
	
}
