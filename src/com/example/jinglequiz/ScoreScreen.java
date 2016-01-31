package com.example.jinglequiz;

import java.util.Iterator;
import java.util.TreeSet;
import android.widget.TextView;
import sofia.app.ShapeScreen;

// -------------------------------------------------------------------------
/**
 * Screen that displays the top five high scores that have been achieved when
 * playing the JingleQuiz game. Retrieves and displays scores automatically upon
 * initialization.
 *
 * @author Divyansh Gupta (divyg), Sean Crenshaw (seanpc9), Jordan White
 *         (jordanrw)
 * @version 2014.12.01
 */
public class ScoreScreen extends ShapeScreen {
	// ~ Fields ................................................................
	private TextView display;

	// ~ Methods ...............................................................
	// ----------------------------------------------------------
	/**
	 * Initialize the screen and display top 5 Scores.
	 */
	public void initialize() {
		this.displayScores();
	}

	// ----------------------------------------------------------
	/**
	 * Gets top 5 scores from SharedPreferences of this application and displays
	 * them for user to see.
	 */
	public void displayScores() {
		String toDisplay = "You have not played yet!";
		TreeSet<Integer> scores = new TreeSet<Integer>(
		    ScoreKeeper.keepOnlyTopScores(5, this.getApplicationContext()));
		if (scores.size() > 0) {
			toDisplay = "";
			for (Iterator<Integer> iter = scores.descendingIterator(); iter
			    .hasNext();) {
				Integer score = iter.next();
				toDisplay += score + "\n\n";
			}
		}
		display.setText(toDisplay);
	}

	// ----------------------------------------------------------
	/**
	 * Redirects back to the main menu when backToMenu button is clicked.
	 */
	public void backToMenuClicked() {
		this.presentScreen(WelcomeScreen.class);
	}
}
