package org.grayleaves.sweater;

import java.util.HashMap;
import java.util.Map;



public class SimpleYarnService implements YarnService {

	private Map<YarnEnum, Integer> yarn = new HashMap<>(); 
	public SimpleYarnService(YarnEnum color, int yards) {
		yarn.put(color, yards);
	}

	@Override
	public int getYarn(YarnEnum color, int yards) {
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
