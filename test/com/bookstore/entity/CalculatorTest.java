package com.bookstore.entity;

import static org.junit.Assert.*;

import org.junit.Test;

public class CalculatorTest {

	@Test
	public void testAdd() {
		Calculator cal = new Calculator();
		int a = 5;
		int b = 12;
		
		long result = cal.add(a, b);
		
		long expected = 17;
		
		assertEquals(expected, result);
		
	}

}
