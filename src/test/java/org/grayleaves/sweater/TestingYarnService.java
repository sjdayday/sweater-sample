package org.grayleaves.sweater;

import java.util.HashMap;
import java.util.Map;

public class TestingYarnService implements YarnService {

	private Map<KnitStatusResponseEnum, Integer> yarn = new HashMap<>(); 
	
	public TestingYarnService(KnitStatusResponseEnum color, int yards) {
		yarn.put(color, yards);
	}

	@Override
	public int getYarn(KnitStatusResponseEnum color, int yards) {
		int available = 0; 
		if (yarn.containsKey(color)) {
			available = yarn.get(color);
			if (available > yards) {
				available = yards; 
			}
		}
		return available;
	}

}
