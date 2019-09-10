package com.ricardobevi.transport;

import org.junit.Test;

import java.util.Optional;

import static junit.framework.TestCase.assertEquals;

public class TransportTest {
	
	@Test
	public void givenAInteger10ReturnInteger10(){
		
		Transport transport = Transport.builder(TransportTest.class)
			.putInt("id", 10)
			.build();
		
		Integer id = transport.getInt("id");
		
		assertEquals(Optional.of(10), Optional.of(id));
	}
	
	@Test
	public void givenAStringLalaReturnStringLala(){
		
		Transport transport =  Transport.builder(TransportTest.class)
			.putString("id", "lala")
			.build();
		
		String id = transport.getString("id");
		
		assertEquals("lala", id);
	}
	
	
	@Test(expected = MissingKeyException.class)
	public void givenAMissingStringKeyShouldThrowException(){
		
		Transport transport = Transport.builder(TransportTest.class).build();
		
		transport.getString("id");
		
	}
	
	@Test(expected = MissingKeyException.class)
	public void givenAMissingIntKeyShouldThrowException(){
		
		Transport transport = Transport.builder(TransportTest.class).build();
		
		transport.getInt("id");
		
	}

}
