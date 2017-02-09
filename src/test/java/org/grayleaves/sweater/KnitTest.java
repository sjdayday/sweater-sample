package org.grayleaves.sweater;

import static org.junit.Assert.assertEquals;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.grayleaves.utility.Clock;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

//FIXME would like to use glassfish for serverless testing without introducing glassfish in prod
// would need to instantiate a javax.ws.rs.core.Application for testing, 
// not the subclass org.glassfish.jersey.server.ResourceConfig, as here 
public class KnitTest extends JerseyTest {

	@Override
	protected Application configure() {
		return new TestingApiV1App();
	}

	@Override
	@Before
	public void setUp() throws Exception {
		super.setUp();
		Clock.setDateForTesting("10/15/2005 12:00:14 PM");
		StatusResponse.forceDelay(0); 
		StatusResponse.hang(false); 
		StatusResponse.throwExceptions(false); 
	}
	
	@Test
	public void knitReturnsStitchesResponseAlongWithNormalStatusResponse() {
		ControlResponse controlResponse = target("v1.1/color/gray").request().get(ControlResponse.class);  
//		assertEquals("setColor", controlResponse.getCommand()); 
		assertEquals("GRAY", controlResponse.getColor()); 

		KnitStatusResponse knitResponse = target("v1.1/knit/150").request().get(KnitStatusResponse.class);  
		assertEquals("150 stitches knitted using 1 yards of GRAY yarn", knitResponse.getKnitResponse());

		assertEquals(StatusResponse.NAME, knitResponse.getName()); 
		assertEquals(0, knitResponse.getDelay()); 
		assertEquals(0, knitResponse.getElapsedTime()); 
		assertEquals(StatusResponse.NORMAL, knitResponse.getResponse()); 
	}
	@Override
	@After
	public void tearDown() throws Exception {
		super.tearDown();
		Clock.reset(); 
		StatusResponse.forceDelay(0); 
		StatusResponse.hang(false); 
		StatusResponse.throwExceptions(false); 
	}

	@ApplicationPath("/api/*")
	private class TestingApiV1App extends ResourceConfig {
	    public TestingApiV1App() {
	        packages("org.grayleaves.sweater");
	        ApiV1App.configure(); 
	    }
    }
}
