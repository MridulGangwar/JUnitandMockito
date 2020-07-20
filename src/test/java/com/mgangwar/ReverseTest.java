package com.mgangwar;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.BeforeClass;
import org.junit.Test;

public class ReverseTest {

	static Reverse reverse;
	
	@BeforeClass
	public static void setup() {
		reverse = new Reverse();
	}
	
	
	@Test
	public void testReverseWords_SingleWord() {	
		assertEquals("PAS",reverse.reverseWords("SAP"));
		assertEquals("ludirM",reverse.reverseWords("Mridul"));
	}
	
	@Test
	public void testReverseWords_TwoWords() {
		assertEquals("llabtoof maet",reverse.reverseWords("football team"));
	}
	
	@Test
	public void testIsPalindrome_False() {
		assertEquals(false,reverse.isPalindrome("football team"));
		assertFalse(reverse.isPalindrome("football team"));
		
	}
	
	@Test
	public void testIsPalindrome_True() {
		assertEquals(true,reverse.isPalindrome("A man, a plan, a canal: Panama"));
		assertTrue(reverse.isPalindrome("A man, a plan, a canal: Panama"));
		
	}
	
	@Test
	public void testArray() {
		int[] numbers = {12,3,5,7};
		int[] expected = {3,5,7,12};
		Arrays.sort(numbers);
		assertArrayEquals(expected,numbers);
	}
	
	@Test(expected=NullPointerException.class)
	public void testArray_NULL() {
		int[] numbers = null;
		Arrays.sort(numbers);
	}
	
	@Test(timeout=100)
	public void testSortingPerformance() {
		int[] array = {14,53,14,6,2,63,1};
		for (int i =0; i<1000000; i++) {
			array[0]=i;
			Arrays.sort(array);
		}
	}
}
