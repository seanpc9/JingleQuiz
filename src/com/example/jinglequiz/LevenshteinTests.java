package com.example.jinglequiz;

import student.TestCase;

// -------------------------------------------------------------------------
/**
 * Test cases for the Levenshtein distance algorithm.
 *
 * @author Divyansh Gupta (divyg), Sean Crenshaw (seanpc9), Jordan White
 *         (jordanrw)
 * @version 2014.11.13
 */
public class LevenshteinTests extends TestCase {

	// ~ Public methods ..................................................
	// ----------------------------------------------------------
	/**
	 * Tests the distance method.
	 */
	public void testDistance() {
		Levenshtein leven1 = new Levenshtein("start", "cart");
		assertEquals(leven1.distance(), 2);
		Levenshtein leven2 = new Levenshtein("This is a very long string",
		    "And so is this very long string");
		assertEquals(leven2.distance(), 9);
	}
}
