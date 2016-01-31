package com.example.jinglequiz;

import android.content.Context;
import android.test.InstrumentationTestCase;

// -------------------------------------------------------------------------
/**
 * Tests the ScreenCounter class for proper design and functionality.
 *
 * @author Divyansh Gupta (divyg), Sean Crenshaw (seanpc9), Jordan White
 *         (jordanrw)
 * @version 2014.12.01
 */
public class ScreenCounterTest extends InstrumentationTestCase {
	// ~ Fields ................................................................
	private ScreenCounter counter;

	// ~ Methods ..............................................................
	// ----------------------------------------------------------
	/**
	 * Set up the test scenario for each test method.
	 */
	public void setUp() {
		Context context = getInstrumentation().getTargetContext();
		counter = new ScreenCounter(context, null);
	}

	// ----------------------------------------------------------
	/**
	 * Test method incrementUp()
	 */
	public void testIncrementUp() {
		counter.incrementUp();
		assertEquals("1/10", counter.getText());

		for (int i = 0; i < 9; i++) {
			counter.incrementUp();
		}

		counter.incrementUp();
		assertEquals("10/10", counter.getText());
	}

	// ----------------------------------------------------------
	/**
	 * Test method for incrementDown()
	 */
	public void testIncrementDown() {
		counter.incrementDown();
		assertEquals("-1/10", counter.getText());
	}

	// ----------------------------------------------------------
	/**
	 * Test method for setEndCount()
	 */
	public void testSetEndCount() {
		counter.setEndCount(25);
		assertEquals("0/25", counter.getText());
	}

	// ----------------------------------------------------------
	/**
	 * Test method for setCurrent()
	 */
	public void testSetCurrent() {
		counter.setCurrent(5);
		assertEquals("5/10", counter.getText());
	}

}
