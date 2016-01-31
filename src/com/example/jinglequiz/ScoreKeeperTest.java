package com.example.jinglequiz;

import java.util.ArrayList;
import java.util.TreeSet;
import java.util.Set;
import android.content.Context;
import android.test.InstrumentationTestCase;

// -------------------------------------------------------------------------
/**
 * Tests the ScoreKeeper class for proper functionality and design.
 *
 * @author Divyansh Gupta (divyg), Sean Crenshaw (seanpc9), Jordan White
 *         (jordanrw)
 * @version 2014.12.01
 */

@SuppressWarnings("static-access")
public class ScoreKeeperTest extends InstrumentationTestCase {

	private ScoreKeeper scoreKeeper;
	private Context context;

	// ----------------------------------------------------------
	/**
	 * Set up the test scenario for each test method.
	 */
	public void setUp() {
		context = getInstrumentation().getTargetContext();
		scoreKeeper = new ScoreKeeper(context);
	}

	// ----------------------------------------------------------
	/**
	 * Test method for setScore() & getScore()
	 */
	public void testSetScore() {
		scoreKeeper.setScore(25);
		assertEquals(25, scoreKeeper.getScore());
	}

	// ----------------------------------------------------------
	/**
	 * Test method for
	 */
	public void testIncrementScore() {
		scoreKeeper.setScore(15);
		scoreKeeper.incrementScore(25);

		assertEquals(40, scoreKeeper.getScore());
	}

	// ----------------------------------------------------------
	/**
	 * Test method for saveCurrentScore() & getStoredScores()
	 */
	public void testSaveCurrentScore() {
		scoreKeeper.setScore(15);
		scoreKeeper.incrementScore(10);
		scoreKeeper.saveCurrentScore();

		Set<Integer> setTheTable = new TreeSet<Integer>();
		setTheTable.add(25);
		setTheTable.add(85);
		setTheTable.add(94);
		setTheTable.add(114);

		assertEquals(setTheTable, scoreKeeper.getStoredScores(context));
	}

	// ----------------------------------------------------------
	/**
	 * Test method for storeScore() & getStoredScores()
	 */
	public void testStoreScore() {
		scoreKeeper.setScore(7);
		scoreKeeper.saveCurrentScore();
		scoreKeeper.storeScore(93, context);

		Set<Integer> setTheTable = new TreeSet<Integer>();
		setTheTable.add(93);
		setTheTable.add(7);
		setTheTable.add(25);
		setTheTable.add(85);
		setTheTable.add(94);
		setTheTable.add(114);

		assertEquals(setTheTable, scoreKeeper.getStoredScores(context));
	}

	// ----------------------------------------------------------
	/**
	 * Test method for storeScores() & getStoredScores()
	 */
	public void testStoreScores() {
		ArrayList<Integer> list = new ArrayList<Integer>();
		list.add(58);
		list.add(85);

		scoreKeeper.storeScores(list, context);

		Set<Integer> setTheTable = new TreeSet<Integer>();
		setTheTable.add(85);
		setTheTable.add(58);
		assertEquals(setTheTable, scoreKeeper.getStoredScores(context));
	}

	// ----------------------------------------------------------
	/**
	 * Test method for keepOnlyTopScores() when amount of scores to keep is
	 * larger than the size of the list
	 */
	public void testKeepOnlyTopScores() {
		// store 5 scores
		ArrayList<Integer> list = new ArrayList<Integer>();
		list.add(22);
		list.add(58);
		list.add(85);
		list.add(94);
		list.add(114);
		scoreKeeper.storeScores(list, context);

		// what new list should be
		Set<Integer> setTheTable = new TreeSet<Integer>();
		setTheTable.add(22);
		setTheTable.add(58);
		setTheTable.add(85);
		setTheTable.add(94);
		setTheTable.add(114);

		assertEquals(setTheTable, scoreKeeper.keepOnlyTopScores(75, context));
	}

	// ----------------------------------------------------------
	/**
	 * Test method for keepOnlyTopScores() when the number of scores to keep is
	 * less than the size of the list of scores
	 */
	public void testKeepOnlyTopScores2() {
		// store 5 scores
		ArrayList<Integer> list = new ArrayList<Integer>();
		list.add(22);
		list.add(58);
		list.add(85);
		list.add(94);
		list.add(114);
		scoreKeeper.storeScores(list, context);

		// what new list should be
		Set<Integer> setTheTable = new TreeSet<Integer>();
		setTheTable.add(85);
		setTheTable.add(94);
		setTheTable.add(114);

		assertEquals(setTheTable, scoreKeeper.keepOnlyTopScores(3, context));
	}
}
