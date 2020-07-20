package com.mgangwar;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class ParameterizedReverseTest {
	
	Reverse reverse = new Reverse();
	private String input;
	private String expectedOutput;
	
	public ParameterizedReverseTest(String input, String expectedOutput) {
		super();
		this.input = input;
		this.expectedOutput = expectedOutput;
	}


	@Parameters
	public static Collection<String[]> testConditions() {
		String expectedOutputs[][] = {{"SAP","PAS"},
									  {"football team","llabtoof maet"}};
		return Arrays.asList(expectedOutputs);
	}
	
	@Test
	public void testReverseWords() {	
		assertEquals(expectedOutput,reverse.reverseWords(input));
	}
	
}
