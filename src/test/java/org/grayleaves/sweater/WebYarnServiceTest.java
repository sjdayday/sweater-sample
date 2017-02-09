package org.grayleaves.sweater;

import static org.junit.Assert.*;

import java.lang.reflect.Field;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

public class WebYarnServiceTest {

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
//	@Test
//	public void test() {
//		Map<String, String> newenv = new HashMap<>();
//		newenv.put(WebYarnService.YARN_SERVICE_URL, "http://yarn.mybluemix.net/api/v1");
//		setEnv(newenv); 
//		yarnService = new WebYarnService(); 
//		System.out.println("using: "+yarnService.getYarn(YarnEnum.GRAY, 5));
//	}
	
    // this hack useful for unit tests only
	//http://stackoverflow.com/questions/318239/how-do-i-set-environment-variables-from-java  
	protected static void setEnv(Map<String, String> newenv)
	{
	  try
	    {
	        Class<?> processEnvironmentClass = Class.forName("java.lang.ProcessEnvironment");
	        Field theEnvironmentField = processEnvironmentClass.getDeclaredField("theEnvironment");
	        theEnvironmentField.setAccessible(true);
	        Map<String, String> env = (Map<String, String>) theEnvironmentField.get(null);
	        env.putAll(newenv);
	        Field theCaseInsensitiveEnvironmentField = processEnvironmentClass.getDeclaredField("theCaseInsensitiveEnvironment");
	        theCaseInsensitiveEnvironmentField.setAccessible(true);
	        Map<String, String> cienv = (Map<String, String>)     theCaseInsensitiveEnvironmentField.get(null);
	        cienv.putAll(newenv);
	    }
	    catch (NoSuchFieldException e)
	    {
	      try {
	        Class[] classes = Collections.class.getDeclaredClasses();
	        Map<String, String> env = System.getenv();
	        for(Class cl : classes) {
	            if("java.util.Collections$UnmodifiableMap".equals(cl.getName())) {
	                Field field = cl.getDeclaredField("m");
	                field.setAccessible(true);
	                Object obj = field.get(env);
	                Map<String, String> map = (Map<String, String>) obj;
	                map.clear();
	                map.putAll(newenv);
	            }
	        }
	      } catch (Exception e2) {
	        e2.printStackTrace();
	      }
	    } catch (Exception e1) {
	        e1.printStackTrace();
	    } 
	}
	
}
