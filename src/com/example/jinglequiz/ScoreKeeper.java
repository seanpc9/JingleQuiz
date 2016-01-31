package com.example.jinglequiz;

import android.content.SharedPreferences.Editor;
import android.content.SharedPreferences;
import android.content.Context;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.SortedSet;
import java.util.Collection;

// -------------------------------------------------------------------------
/**
 * Object that keeps track of Score as game-play proceeds for an application.
 * Can be used to statically store Scores in SharedPreferences of an application
 * as well, and retrieve them for later use.
 *
 * @author Divyansh Gupta (divyg), Sean Crenshaw (seanpc9), Jordan White
 *         (jordanrw)
 * @version 2014.12.01
 */
public final class ScoreKeeper {
	// ~ Fields ................................................................
	private int score;
	private Context context;

	// ~ Constructors ..........................................................
	// ----------------------------------------------------------
	/**
	 * Create a new ScoreKeeper object.
	 *
	 * @param appContext is the Context of the application for which the
	 *            ScoreKeeper will store Scores for.
	 */
	public ScoreKeeper(Context appContext) {
		context = appContext;
		score = 0;
	}

	// ~ Methods ...............................................................
	// ----------------------------------------------------------
	/**
	 * Manually set the score in the ScoreKeeper to this newScore.
	 *
	 * @param newScore is the new Score the ScoreKeeper object should represent.
	 */
	public void setScore(int newScore) {
		score = newScore;
	}

	// ----------------------------------------------------------
	/**
	 * Modify the Score that the ScoreKeeper represents by the amount given by
	 * toIncrement.
	 *
	 * @param toIncrement is the amount to change the score by.
	 */
	public void incrementScore(int toIncrement) {
		score += toIncrement;
	}

	// ----------------------------------------------------------
	/**
	 * Get the current score that the ScoreKeeper object represents.
	 *
	 * @return the current score.
	 */
	public int getScore() {
		return score;
	}

	// ----------------------------------------------------------
	/**
	 * Save the currentScore of the ScoreKeeper in the SharedPreferences of the
	 * given Context (in the constructor of the ScoreKeeper).
	 */
	public void saveCurrentScore() {
		storeScore(score, context);
	}

	// ----------------------------------------------------------
	/**
	 * Get the SharedPreferences folder where the scores are kept by ScoreKeeper
	 * for a given application.
	 *
	 * @param context is the context of the application of which to return
	 *            SharedPreferences for.
	 * @return the SharedPreferences object of where the scores are kept by
	 *         ScoreKeeper.
	 */
	public static SharedPreferences getScorePreferences(Context context) {
		SharedPreferences prefs = context.getSharedPreferences("score_keeper",
		    Context.MODE_PRIVATE);
		return prefs;
	}

	// ----------------------------------------------------------
	/**
	 * Gets the previously stored scores of an application in a sorted list,
	 * ascending.
	 *
	 * @param context is the context of the application of which to get the
	 *            previously stored Scores for.
	 * @return the previously stored Scores of an application in ascending
	 *         order.
	 */
	public static Set<Integer> getStoredScores(Context context) {
		SharedPreferences prefs = getScorePreferences(context);
		SortedSet<Integer> scores = new TreeSet<Integer>();
		Map<String, ?> scoreMap = prefs.getAll();
		for (String key : scoreMap.keySet()) {
			Integer score = (Integer) scoreMap.get(key);
			scores.add(score);
		}
		return scores;
	}

	// ----------------------------------------------------------
	/**
	 * Adds score to the stored list of scores in the application if the score
	 * does not already exist in storage.
	 *
	 * @param score is the score to add if it is unique in storage.
	 * @param context is the Application Context of the application in which to
	 *            store the scores.
	 * @return true if score is unique and storage was changed; false if score
	 *         already existed in storage and nothing was changed.
	 */
	public static boolean storeScore(int score, Context context) {
		SharedPreferences prefs = getScorePreferences(context);
		boolean scoreUnique = !prefs.contains(Integer.toString(score));
		if (scoreUnique) {
			Editor prefEditor = prefs.edit();
			prefEditor.putInt(Integer.toString(score), score);
			prefEditor.apply();
		}
		return scoreUnique;
	}

	// ----------------------------------------------------------
	/**
	 * Overwrite the stored scores in the application's SharedPreferences so
	 * they only contain the Collection of scores passed in to this method.
	 *
	 * @param scores is the list of scores to store in application
	 *            SharedPreferences storage.
	 * @param context is the application context, used to determine in which
	 *            application the scores will be saved.
	 */
	public static void storeScores(Collection<Integer> scores, Context context) {
		Editor editPrefs = getScorePreferences(context).edit();
		editPrefs.clear();
		for (Integer intScore : scores) {
			editPrefs.putInt(Integer.toString(intScore), intScore);
		}
		editPrefs.apply();
	}

	// ----------------------------------------------------------
	/**
	 * Reads an applications StoredPreferences for previously stored scores by
	 * StoreKeeper, and only keeps the highest. The number of high scores to
	 * keep is determined by scoresToKeep parameter.
	 *
	 * @param scoresToKeep is the number of scores to keep in the application's
	 *            SharedPreferences.
	 * @param context is the application context, which determines in which
	 *            application the scores will be saved.
	 * @return the list of scores that have been kept in storage for the
	 *         Application.
	 */
	public static Set<Integer> keepOnlyTopScores(int scoresToKeep,
	    Context context) {
		TreeSet<Integer> storedScores = (TreeSet<Integer>) getStoredScores(context);
		int sizeToKeep = Math.min(storedScores.size(), scoresToKeep);
		TreeSet<Integer> toKeep = new TreeSet<Integer>();
		for (int i = 0; i < sizeToKeep; i++) {
			Integer toStore = storedScores.pollLast();
			toKeep.add(toStore);
		}
		storeScores(toKeep, context);
		return toKeep;
	}
}
