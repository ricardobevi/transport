package com.ricardobevi.transport;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Transport {
	
	public static class Builder {
		private Class generatorClass;
		private Map<String, Object> map;
		
		public Builder(Class generatorClass){
			this.generatorClass = generatorClass;
			this.map = new HashMap<>();
		}
		
		
		public Builder putInt(String key, Integer value) {
			this.map.put(key, value);
			return this;
		}
		
		public Builder putString(String key, String value) {
			this.map.put(key, value);
			return this;
		}
		
		public Transport build(){
			return new Transport(this.generatorClass, Collections.unmodifiableMap(this.map));
		}
		
	}
	
	
	
	private final Class generatorClass;
	private final Map<String, Object> map;
	
	
	private Transport(Class generatorClass, Map<String, Object> map) {
		this.generatorClass = generatorClass;
		this.map = map;
	}
	
	public static Builder builder(Class generatorClass){
		return new Builder(generatorClass);
	}
	
	public Integer getInt(String key) {
		Optional<Object> optionalKeyObject = Optional.ofNullable(map.get(key));
		
		Object keyObject = optionalKeyObject.orElseThrow(MissingKeyException::new);
		
		if (keyObject instanceof Integer) {
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

}
