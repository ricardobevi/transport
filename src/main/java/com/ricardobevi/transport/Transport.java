package com.ricardobevi.transport;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Transport {
	
	private final Class generatorClass;
	private Map<String, Object> map;
	
	
	public Transport(Class generatorClass) {
		this.generatorClass = generatorClass;
		this.map = new HashMap<>();
	}
	
	
	public Integer getInt(String key) {
		if (map.get(key) instanceof Integer) {
			return (Integer) map.get(key);
		} else {
			throw new RuntimeException("Error! not a integer from " + generatorClass.getSimpleName());
		}
	}
	
	public String getString(String key) {
		Optional<Object> optionalKeyObject = Optional.ofNullable(map.get(key));
		
		Object keyObject = optionalKeyObject.orElseThrow(MissingKeyException::new);
		
		if (keyObject instanceof String) {
			return (String) map.get(key);
		} else {
			throw new RuntimeException("Error! not a string from " + generatorClass.getSimpleName());
		}
	}
	
	public void putInt(String key, Integer value) {
		this.map.put(key, value);
	}
	
	public void putString(String key, String value) {
		this.map.put(key, value);
	}
	

}
