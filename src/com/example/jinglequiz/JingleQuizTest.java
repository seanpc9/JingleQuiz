package com.example.jinglequiz;

import sofia.util.Random;
import com.example.jinglequiz.JingleQuiz.GameDifficulty;
import android.content.Context;
import android.test.InstrumentationTestCase;

// -------------------------------------------------------------------------
/**
 * Test the JingleQuiz class for proper design, functionality and
 * implementation.
 *
 * @author Divyansh Gupta (divyg), Sean Crenshaw (seanpc9), Jordan White
 *         (jordanrw)
 * @version 2014.12.01
 */
public class JingleQuizTest extends InstrumentationTestCase {
	// ~ Fields ................................................................
	private JingleQuiz quiz;

	// ~ Methods ...............................................................
	// ----------------------------------------------------------
	/**
	 * Set up the test scenario for each test method.
	 */
	public void setUp() {
		Context context = getInstrumentation().getTargetContext();
		Random.setNextInts(0, 1);
		quiz = new JingleQuiz(context, GameDifficulty.HARD, 2);
	}

	// ----------------------------------------------------------
	/**
	 * Test method for compileRandomJingles
	 */
	public void testCompileRandomJingles() {
		assertNotNull(quiz.getNextJingle());
		assertNotNull(quiz.getNextJingle());
		assertNull(quiz.getNextJingle());
	}

	// ----------------------------------------------------------
	/**
	 * Test the getDifficulty method. .
	 */
	public void testGetDifficulty() {
		assertEquals(quiz.getDifficulty(), GameDifficulty.HARD);
	}

	// ----------------------------------------------------------
	/**
	 * Test the verifyJingle method.
	 */
	public void testVerifyJingle() {
		Jingle first = quiz.getNextJingle();
		assertEquals(quiz.verifyJingle(first, "Ace Hard"),
		    "Unfortunately, your guess is incorrect.\n"
		        + first.company().get(0) + " is the correct answer.");
		assertEquals(quiz.verifyJingle(first, "Alka Seltzer"),
		    "Great guess, you are correct!");
	}

	// ----------------------------------------------------------
	/**
	 * Test the getScoreKeeper method.
	 */
	public void testGetScoreKeeper() {
		assertNotNull(quiz.getScoreKeeper());
	}

}
