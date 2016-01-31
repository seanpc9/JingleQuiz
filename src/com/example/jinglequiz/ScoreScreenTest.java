package com.example.jinglequiz;

import android.widget.TextView;
import android.widget.Button;

// -------------------------------------------------------------------------
/**
 * Tests the ScoreScreen class for proper design and functionality.
 *
 * @author Divyansh Gupta (divyg), Sean Crenshaw (seanpc9), Jordan White
 *         (jordanrw)
 * @version 2014.12.01
 */

public class ScoreScreenTest extends student.AndroidTestCase<ScoreScreen> {
	// ~ Fields ................................................................
	private Button backToMenu;
	private TextView display;

	// ~ Constructors ..........................................................
	// ----------------------------------------------------------
	/**
	 * Test cases that extend AndroidTestCase must have a parameterless
	 * constructor that calls super() and passes it the screen/activity class
	 * being tested.
	 */
	public ScoreScreenTest() {
		super(ScoreScreen.class);
	}

	// ~ Methods ...............................................................
	// ----------------------------------------------------------
	/**
	 * Set up the test scenario for each test method.
	 */
	public void setUp() {
		// Purposefully left blank.
	}

	// ----------------------------------------------------------
	/**
	 * Test the initialize method.
	 */
	public void testInitializeAndDisplayScores() {
		// initialize calls displayScores, so same test
		assertEquals("93\n\n25\n\n7\n\n", display.getText().toString());
	}

	// ----------------------------------------------------------
	/**
	 * Test backToMenuClicked method.
	 */
	public void testBackToMenuClicked() {
		// prepareForUpcomingActivity(Intent.ACTION_VIEW);
		// click(backToMenu);
		assertTrue(backToMenu.isClickable());
	}

}
