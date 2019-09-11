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
	
	public Byte getByte(String key) {
		return (Byte) checkTypeAndGetObject(key, Byte.class);
	}
	
	public Short getShort(String key) {
		return (Short) checkTypeAndGetObject(key, Short.class);
	}
	
	public Integer getInt(String key) {
		return (Integer) checkTypeAndGetObject(key, Integer.class);
	}
	
	public Long getLong(String key) {
		return (Long) checkTypeAndGetObject(key, Long.class);
	}
	
	public Float getFloat(String key) {
		return (Float) checkTypeAndGetObject(key, Float.class);
	}
	
	public Double getDouble(String key) {
		return (Double) checkTypeAndGetObject(key, Double.class);
	}
	
	public Boolean getBoolean(String key) {
		return (Boolean) checkTypeAndGetObject(key, Boolean.class);
	}
	
	public Character getCharacter(String key) {
		return (Character) checkTypeAndGetObject(key, Character.class);
	}
	
	public String getString(String key) {
		return (String) checkTypeAndGetObject(key, String.class);
	}
	
	
	public List<Transport> getList(String key) {
		return (List) checkTypeAndGetObject(key, List.class);
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
