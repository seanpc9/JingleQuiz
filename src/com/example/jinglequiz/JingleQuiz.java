package com.example.jinglequiz;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Arrays;
import sofia.util.Random;
import android.content.Context;

// -------------------------------------------------------------------------
/**
 * Class that runs the back-end for the JingleQuiz Android application. Compiles
 * a list of randomly produced Jingles, and upon user-input from GameScreen
 * object, determines if user correctly guessed the Jingle and awards user a
 * score.
 *
 * @author Divyansh Gupta (divyg), Sean Crenshaw (seanpc9), Jordan White
 *         (jordanrw)
 * @version 2014.12.01
 */
public class JingleQuiz {
	// ~ Fields ................................................................

	/**
	 * Represents the number of Jingles that will be produced with each
	 * JingleQuiz.
	 */
	private final int JINGLE_COUNT;

	private int allowedError;
	private GameDifficulty difficulty;
	private Deque<Jingle> jingles;
	private ScoreKeeper scoreKeeper;
	private int scoreMultiplier;
	private int reward;

	// -------------------------------------------------------------------------
	/**
	 * Represents the difficulty level for a Game.
	 */
	public enum GameDifficulty {
		/**
		 * Represents an easy Game.
		 */
		EASY,

		/**
		 * Represents a Game of Medium difficulty.
		 */
		MEDIUM,

		/**
		 * Represents a very difficult Game.
		 */
		HARD;
	}

	// ~ Constructors ..........................................................
	// ----------------------------------------------------------
	/**
	 * Create a new JingleQuiz object.
	 *
	 * @param context is the Context for which the JingleQuiz is used in the
	 *            Android Application.
	 * @param newDifficulty is the Difficulty of the JingleQuiz, or how hard it
	 *            will be for the player.
	 * @param newJingleCount is the number of Jingles that will be produced with
	 *            each JingleQuiz.
	 */
	public JingleQuiz(Context context, GameDifficulty newDifficulty,
	    int newJingleCount) {
		JINGLE_COUNT = newJingleCount;
		this.setDifficulty(newDifficulty);
		this.compileRandomJingles(context);
		scoreKeeper = new ScoreKeeper(context);
	}

	// ~ Methods ...............................................................
	// ----------------------------------------------------------
	/**
	 * Compile and create a list of random Jingles to quiz the user on. Number
	 * of Jingles created based on JINGLE_COUNT field.
	 *
	 * @param context is the Context of the Android Application from which the
	 *            JingleList will be found and compiled.
	 */
	public void compileRandomJingles(Context context) {
		jingles = new ArrayDeque<Jingle>(JINGLE_COUNT);
		String[] arrJingles = context.getResources().getStringArray(
		    R.array.JingleList);
		for (int i = 0; i < JINGLE_COUNT; i++) {
			Jingle randomJingle = this.randomJingle(arrJingles);
			jingles.push(randomJingle);
		}
	}

	// ----------------------------------------------------------
	/**
	 * Produces a single random Jingle from those available in the application
	 * in the XML String Array in res/values.
	 *
	 * @return a single randomly produced Jingle that is available to the
	 *         application.
	 */
	private Jingle randomJingle(String[] arrJingles) {
		int jingleCount = arrJingles.length;
		int randomNum = Random.generator().nextInt(jingleCount);
		LinkedList<String> splitJingle = new LinkedList<String>(
		    Arrays.asList(arrJingles[randomNum].split("\\|")));
		String soundFile = splitJingle.removeFirst();
		Jingle randomJingle = new Jingle(soundFile, splitJingle);
		if (jingles.contains(randomJingle)) {
			randomJingle = this.randomJingle(arrJingles);
		}
		return randomJingle;
	}

	// ----------------------------------------------------------
	/**
	 * Called at creation of JingleQuiz. Sets the allowedError for user guesses,
	 * and rewards and scoreMultiplier for correct guesses, based on the
	 * GameDifficulty level of this JingleQuiz.
	 */
	private void setDifficulty(GameDifficulty newDifficulty) {
		this.difficulty = newDifficulty;
		switch (difficulty) {
			case EASY:
				allowedError = 3;
				reward = 7;
				scoreMultiplier = 1;
				break;
			case MEDIUM:
				allowedError = 2;
				reward = 8;
				scoreMultiplier = 2;
				break;
			case HARD:
				allowedError = 1;
				reward = 9;
				scoreMultiplier = 3;
				break;
		}
	}

	// ----------------------------------------------------------
	/**
	 * Get the user-defined game difficulty of the JingleQuiz.
	 *
	 * @return the difficulty of the JingleQuiz.
	 */
	public GameDifficulty getDifficulty() {
		return difficulty;
	}

	// ----------------------------------------------------------
	/**
	 * Returns the next Jingle in the Jingle Quiz. Returns null if no Jingles
	 * are left in the JingleQuiz.
	 *
	 * @return a Jingle if the JingleQuiz has more Jingles to ask the user.
	 */
	public Jingle getNextJingle() {
		return jingles.pollFirst();
	}

	// ----------------------------------------------------------
	/**
	 * Checks a guess by the user to see if user was able to correctly guess a
	 * Jingle's company name.
	 *
	 * @param toVerify is the Jingle that the user was attempting to guess the
	 *            company for.
	 * @param guessedName is the companyName that the user guessed.
	 * @return success message if user guessed the Jingle's company name to a
	 *         satisfactory level (set by the allowedError, which is in turn set
	 *         by the GameDifficulty of this JingleQuiz). Otherwise, returns
	 *         indication that guess was incorrect and the actual companyName of
	 *         the jingle.
	 */
	public String verifyJingle(Jingle toVerify, String guessedName) {
		int guessError = toVerify.verifyDistance(guessedName);
		if (guessError <= allowedError) {
			this.updateScore();
			return "Great guess, you are correct!";
		}
		String company = toVerify.company().get(0);
		return "Unfortunately, your guess is incorrect.\n" + company
		    + " is the correct answer.";
	}

	// ----------------------------------------------------------
	/**
	 * Called when user correctly guesses a Jingle's companyName.
	 */
	private void updateScore() {
		int toAdd = reward * scoreMultiplier;
		scoreKeeper.incrementScore(toAdd);
	}

	// ----------------------------------------------------------
	/**
	 * Get the ScoreKeeper object associated with this JingleQuiz.
	 *
	 * @return the scoreKeeper that is keeping track of score for this Game.
	 */
	public ScoreKeeper getScoreKeeper() {
		return scoreKeeper;
	}
}
