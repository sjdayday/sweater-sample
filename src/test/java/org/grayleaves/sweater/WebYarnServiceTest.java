package org.grayleaves.sweater;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.core.Application;

import org.junit.Before;
import org.junit.Test;

public class WebYarnServiceTest extends EnvironmentTest {

	private WebYarnService yarnService;

	@Before
	public void setUp() {
		Map<String, String> newenv = new HashMap<>();
		newenv.put(WebYarnService.YARN_SERVICE_URL, "http://some.test.url/");
		setEnv(newenv); 
	}
	
	@Test
	public void urlBuiltFromEnvironmentVariable() {
		yarnService = new WebYarnService(); 
		assertEquals("http://some.test.url/", yarnService.getTargetURL());
	}
	
	
}
