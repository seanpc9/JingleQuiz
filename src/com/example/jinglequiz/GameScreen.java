package com.example.jinglequiz;

import com.example.jinglequiz.JingleQuiz.GameDifficulty;
import android.view.inputmethod.InputMethodManager;
import android.content.Context;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Button;
import sofia.app.ShapeScreen;

// -------------------------------------------------------------------------
/**
 * The Screen upon which JingleQuiz game-play will take place. Plays Jingle
 * audio and waits for user input, then checks to see if user guessed Jingle
 * correctly.
 *
 * @author Divyansh Gupta (divyg), Sean Crenshaw (seanpc9), Jordan White
 *         (jordanrw)
 * @version 2014.11.17
 */
public class GameScreen extends ShapeScreen {
	// ~ Fields ................................................................
	/**
	 * Represents the number of Jingles that will be produced with each
	 * JingleQuiz game.
	 */
	private final int JINGLE_COUNT = 10;

	private JingleQuiz quiz;
	private Jingle currentJingle;
	private JinglePlayer jPlayer;
	private ScreenCounter counter;
	private Button replay;
	private Button enterGuess;
	private EditText guess;
	private TextView answer;
	private TextView scoreView;
	private boolean answerGiven;

	// ~ Methods ...............................................................
	// ----------------------------------------------------------
	/**
	 * Creates the GameScreen and sets it up for game-play.
	 *
	 * @param newDifficulty is the GameDifficulty
	 */
	public void initialize(GameDifficulty newDifficulty) {
		quiz = new JingleQuiz(this, newDifficulty, JINGLE_COUNT);
		jPlayer = new JinglePlayer(this);
		counter.setEndCount(JINGLE_COUNT);
		this.nextJingle();
	}

	// ----------------------------------------------------------
	/**
	 * Gets the next Jingle from the JingleQuiz and begins playing it.
	 */
	private boolean nextJingle() {
		currentJingle = quiz.getNextJingle();
		if (currentJingle != null) {
			playJingle();
			this.resetForNextJingle();
			return true;
		}
		return false;
	}

	// ----------------------------------------------------------
	/**
	 * Play the currentJingle for the player to guess.
	 */
	public void playJingle() {
		if (currentJingle != null) {
			jPlayer.playJingle(currentJingle);
		}
	}

	// ----------------------------------------------------------
	/**
	 * Set whether or not the GUI for the GameScreen is user manipulatable or
	 * not. Generally set to false when Jingle is playing, and is re-enabled
	 * when it is time for user to guess the name of the Jingle.
	 *
	 * @param guiEnabled decides whether or not to enable the GUI
	 */
	public void setGUIEnabled(boolean guiEnabled) {
		guess.setClickable(guiEnabled);
		enterGuess.setClickable(guiEnabled);
		replay.setClickable(guiEnabled);
	}

	// ----------------------------------------------------------
	/**
	 * Replay the current Jingle when the replay button is clicked.
	 */
	public void replayClicked() {
		this.playJingle();
	}

	// ----------------------------------------------------------
	/**
	 * Event handler for when button with ID guess is clicked. If Button is
	 * clicked before answer for a Jingle is given out, Screen updates to check
	 * the given user answer and updates screen to reflect this.
	 */
	public void enterGuessClicked() {
		this.closeKeyboard();
		if (currentJingle == null) {
			this.presentScreen(ScoreScreen.class);
		}
		if (answerGiven) {
			this.nextJingle();
			if (currentJingle == null) {
				quiz.getScoreKeeper().saveCurrentScore();
				this.presentScreen(ScoreScreen.class);
			}
			return;
		}
		checkAndDisplayAnswer();
	}

	// ----------------------------------------------------------
	/**
	 * When Editing is done to the "guess" EditView, treat it similar to
	 * clicking the "enterGuess" Button.
	 */
	public void guessEditingDone() {
		enterGuessClicked();
	}

	// ----------------------------------------------------------
	/**
	 * Close the soft keyboard on the screen.
	 */
	public void closeKeyboard() {
		InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.hideSoftInputFromWindow(guess.getWindowToken(), 0);
	}

	// ----------------------------------------------------------
	/**
	 * Reset the GameScreen to play the next Jingle and to accept guesses for
	 * the company that produces the Jingle.
	 */
	private void resetForNextJingle() {
		enterGuess.setText(R.string.enter_your_guess);
		answer.setText(""); // TODO: use answer TextView as score keeping?
		guess.setText("");
		counter.incrementUp();
		answerGiven = false;
	}

	// ----------------------------------------------------------
	/**
	 * Check the Jingle company name that was guessed by the user of the Screen,
	 * and display if the user is correct or incorrect.
	 */
	private void checkAndDisplayAnswer() {
		String strAnswer = quiz.verifyJingle(currentJingle, guess.getText()
		    .toString());
		answer.setText(strAnswer);
		enterGuess.setText(R.string.ready_for_the_next_jingle);
		scoreView.setText("Score: " + quiz.getScoreKeeper().getScore());
		answerGiven = true;
	}

	// ----------------------------------------------------------
	/**
	 * Overrides the onPause() method to also pause JinglePlayer music if it is
	 * playing.
	 */
	@Override
	public void onPause() {
		super.onPause();
		if (jPlayer != null && jPlayer.isPlaying()) {
			jPlayer.pause();
		}
	}

	// ----------------------------------------------------------
	/**
	 * When Screen is resumed, Screen will also resume playing Jingles if any
	 * Jingle was paused previously (likely due to onPause() method).
	 */
	@Override
	public void onPostResume() {
		super.onPostResume();
		if (jPlayer != null && jPlayer.isPaused()) {
			jPlayer.start();
		}
	}

	// ----------------------------------------------------------
	/**
	 * When Screen is destroyed, the JinglePlayer associated with it is released
	 * and freed up for Garbage Collection.
	 */
	@Override
	public void onDestroy() {
		super.onDestroy();
		if (jPlayer != null) {
			jPlayer.release();
			jPlayer = null;
		}
	}

	// ----------------------------------------------------------
	/**
	 * Return the current jingle.
	 *
	 * @return the current jingle
	 */
	public Jingle getCurrentJingle() {
		return currentJingle;
	}

	// ----------------------------------------------------------
	/**
	 * Return the jingle player.
	 *
	 * @return the jingle player.
	 */
	public JinglePlayer getPlayer() {
		return jPlayer;
	}
}
