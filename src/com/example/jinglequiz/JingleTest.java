package com.example.jinglequiz;

import android.test.InstrumentationTestCase;
import android.content.Context;
import java.util.ArrayList;
import java.util.List;

// -------------------------------------------------------------------------
/**
 * Tests the Jingle class for proper design and functionality.
 *
 * @author Divyansh Gupta (divyg), Sean Crenshaw (seanpc9), Jordan White
 *         (jordanrw)
 * @version 2014.12.01
 */
public class JingleTest extends InstrumentationTestCase {
	// ~ Fields ................................................................
	private String mp3;
	private List<String> companies;
	private Jingle jingle;

	// ~ Methods ...............................................................
	// ----------------------------------------------------------
	/**
	 * Sets up the test scenario before each test method is run.
	 */
	public void setUp() {
		companies = new ArrayList<String>();
		companies.add("EA Sports");
		companies.add("EA");
		mp3 = "ea_sports";
		jingle = new Jingle(mp3, companies);
	}

	// ~ Tests .................................................................

	// ----------------------------------------------------------
	/**
	 * Tests hashCode()
	 */
	public void testHashCode() {
		int theCode = mp3.hashCode() + companies.get(0).hashCode();
		assertEquals(theCode, jingle.hashCode());
	}

	// ----------------------------------------------------------
	/**
	 * Tests mp3Name()
	 */
	public void testMp3Name() {
		assertEquals("ea_sports", jingle.mp3Name());
	}

	// ----------------------------------------------------------
	/**
	 * Tests company().
	 */
	public void testCompany() {
		assertEquals("EA Sports", jingle.company().get(0));
	}

	// ----------------------------------------------------------
	/**
	 * Test method getRawResourcePath()
	 */
	public void testGetRawResourcePath() {
		Context context = getInstrumentation().getTargetContext();
		String str = "android.resource://" + context.getPackageName() + "/raw/"
		    + mp3;
		assertEquals(str, jingle.getRawResourcePath(context));
	}

	// ----------------------------------------------------------
	/**
	 * Test method for verifyDistance()
	 */
	public void testVerifyDistance() {
		assertEquals(2, jingle.verifyDistance("EA Spor"));
	}

	// ----------------------------------------------------------
	/**
	 * Test method for when it should return true;
	 */
	public void testEqualsObject() {
		String mp32 = "ea_sports";
		List<String> companies2 = new ArrayList<String>();
		companies2.add("EA Sports");
		companies2.add("EA");

		Jingle jingle2 = new Jingle(mp32, companies2);

		assertTrue(jingle2.equals(jingle2));
	}

	// ----------------------------------------------------------
	/**
	 * Tests equalsObject() when it will return false
	 */
	public void testEqualsObjectFalse() {
		assertFalse(jingle.equals("hi there"));
	}
}
