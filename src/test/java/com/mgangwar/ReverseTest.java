package com.mgangwar;

import static org.junit.Assert.*;

import org.junit.Test;

public class ReverseTest {

	Reverse reverse = new Reverse();
	
	@Test
	public void testReverseWords_SingleWord() {	
		assertEquals("PAS",reverse.reverseWords("SAP"));
		assertEquals("ludirM",reverse.reverseWords("Mridul"));
	}
	
	@Test
	public void testReverseWords_TwoWords() {
		assertEquals("llabtoof maet",reverse.reverseWords("football team"));
	}

}
