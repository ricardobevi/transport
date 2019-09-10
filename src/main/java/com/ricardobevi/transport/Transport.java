package com.ricardobevi.transport;

import java.util.*;

public class Transport {
	
	public static class Builder {
		private Class generatorClass;
		private Map<String, Object> map;
		
		public Builder(Class generatorClass){
			this.generatorClass = generatorClass;
			this.map = new HashMap<>();
		}
		
		
		public Builder putInt(String key, Integer value) {
			putNotNull(key, value);
			return this;
		}
		
		public Builder putString(String key, String value) {
			putNotNull(key, value);
			return this;
		}
		
		private void putNotNull(String key, Object value) {
			this.map.put(Objects.requireNonNull(key), Objects.requireNonNull(value));
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
		return (Integer) checkTypeAndGetObject(key, Integer.class);
	}
	
	public String getString(String key) {
		return (String) checkTypeAndGetObject(key, String.class);
	}
	
	private Object checkTypeAndGetObject(String key, Class aClass) {
		Optional<Object> optionalValueObject = Optional.ofNullable(map.get(key));
		
		Object valueObject = optionalValueObject.orElseThrow(MissingKeyException::new);
		
		if ( aClass.isInstance(valueObject) ) {
			return aClass.cast(map.get(key));
		} else {
			throw new RuntimeException("Error! not a " + aClass.getName() + " from " + generatorClass.getSimpleName());
		}
	}

}
