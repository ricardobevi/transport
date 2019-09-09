package com.ricardobevi.transport;

import org.junit.Test;

import java.util.Optional;

import static junit.framework.TestCase.assertEquals;

public class TransportTest {
	
	@Test
	public void givenAInteger10ReturnInteger10(){
		
		Transport transport = new Transport(TransportTest.class);
		
		transport.putInt("id", 10);
		
		Integer id = transport.getInt("id");
		
		assertEquals(Optional.of(10), Optional.of(id));
	}
	
	@Test
	public void givenAStringLalaReturnStringLala(){
		
		Transport transport = new Transport(TransportTest.class);
		
		transport.putString("id", "lala");
		
		String id = transport.getString("id");
		
		assertEquals("lala", id);
	}
	
	
	@Test(expected = MissingKeyException.class)
	public void givenAMissingStringKeyShouldThrowException(){
		
		Transport transport = new Transport(TransportTest.class);
		
		transport.getString("id");
		
	}
	
	@Test(expected = MissingKeyException.class)
	public void givenAMissingIntKeyShouldThrowException(){
		
		Transport transport = new Transport(TransportTest.class);
		
		transport.getInt("id");
		
	}

}
