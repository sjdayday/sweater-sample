package org.grayleaves.sweater;

import static org.junit.Assert.*;

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
public class StatusTest extends JerseyTest {

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
	public void statusReturnsDefaultStatusResponse() {
		StatusResponse statusResponse = target("status").request().get(StatusResponse.class);  
		assertEquals(StatusResponse.NAME, statusResponse.getName()); 
		assertEquals(0, statusResponse.getDelay()); 
		assertEquals(0, statusResponse.getElapsedTime()); 
		assertEquals(StatusResponse.NORMAL, statusResponse.getResponse()); 
	}
	@Test
	public void delayCausesStatusToReturnAfterSetDelay() {
		ControlResponse controlResponse = target("delay/3").request().get(ControlResponse.class);  
		assertEquals("setGlobalDelay", controlResponse.getCommand()); 
		assertEquals(3, controlResponse.getGlobalDelay()); 
		StatusResponse statusResponse = target("status").request().get(StatusResponse.class);  
		assertEquals(3, statusResponse.getDelay()); 
		assertEquals(3, statusResponse.getElapsedTime()); 

		controlResponse = target("delay/0").request().get(ControlResponse.class);  
		assertEquals(0, controlResponse.getGlobalDelay()); 
		statusResponse = target("status").request().get(StatusResponse.class);  
		assertEquals(0, statusResponse.getDelay()); 
		assertEquals(0, statusResponse.getElapsedTime()); 
	}
	@Test
	public void hangCausesStatusToReturnAfterMaxValueTime() {
		ControlResponse controlResponse = target("hang").request().get(ControlResponse.class);  
		assertEquals("setHang", controlResponse.getCommand()); 
		assertEquals(Integer.MAX_VALUE, controlResponse.getGlobalDelay()); 
		assertTrue(controlResponse.isHang()); 
		StatusResponse statusResponse = target("status").request().get(StatusResponse.class);  
		assertEquals(Integer.MAX_VALUE, statusResponse.getDelay()); 
		assertEquals(Integer.MAX_VALUE, statusResponse.getElapsedTime()); 
	}
	@Test
	public void throwCausesStatusToReturnExceptionResponse() {
		ControlResponse controlResponse = target("throw").request().get(ControlResponse.class);  
		assertEquals("setThrowExceptions", controlResponse.getCommand()); 
		assertTrue(controlResponse.isThrowException()); 
		StatusResponse statusResponse = target("status").request().get(StatusResponse.class);  
		assertEquals(StatusResponse.EXCEPTION, statusResponse.getResponse()); 
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

	@ApplicationPath("/api/v1/*")
	private class TestingApiV1App extends ResourceConfig {
	    public TestingApiV1App() {
	        packages("org.grayleaves.sweater");
	    }	
    }
}
