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
public class HealthTest extends JerseyTest {

	@Override
	protected Application configure() {
		return new TestingApiV1App();
	}

	@Override
	@Before
	public void setUp() throws Exception {
		super.setUp();
	}
	
	@Test
	public void healthReturnsStatusUp() {
		HealthResponse healthResponse = target("v1.1/health").request().get(HealthResponse.class);  
		assertEquals("UP", healthResponse.getStatus()); 
	}
	@Override
	@After
	public void tearDown() throws Exception {
		super.tearDown();
	}

	@ApplicationPath("/api/*")
	private class TestingApiV1App extends ResourceConfig {
	    public TestingApiV1App() {
	        packages("org.grayleaves.sweater");
	    }	
    }
}
