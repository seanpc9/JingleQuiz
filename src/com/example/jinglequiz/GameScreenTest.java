package com.example.jinglequiz;

// import android.widget.TextView;
import android.widget.EditText;
import android.widget.Button;
import com.example.jinglequiz.GameScreen;

// -------------------------------------------------------------------------
/**
 * Tests the GameScreen class for proper design and functionality.
 *
 * @author Divyansh Gupta (divyg), Sean Crenshaw (seanpc9), Jordan White
 *         (jordanrw)
 * @version 2014.12.01
 */

public class GameScreenTest extends student.AndroidTestCase<GameScreen> {

	// ~ Fields ................................................................

	// GUI items
	private Button replay;
	private Button enterGuess;
	private EditText guess;

	// private TextView answer;
	// private TextView scoreView;

	// ~ Tests .................................................................
	/**
	 * Create a new GameScreenTest object.
	 */
	public GameScreenTest() {
		super(GameScreen.class);
	}

	// ~ Methods ...............................................................
	// ----------------------------------------------------------
	/**
	 * Does the setup for each test method for the WelcomeScreenTests.
	 */
	public void setUp() {
		// Intentionally does nothing.
	}

	// ----------------------------------------------------------
	/**
	 * Test onDestroy method.
	 */
	public void testOnDestroy() {
		this.getScreen().onDestroy();
		assertNull(this.getScreen().getPlayer());
	}

	// ----------------------------------------------------------
	/**
	 * Test initialize method.
	 */
	public void testInitializeGameDifficulty() {
		this.getScreen().initialize();
		assertNull(this.getScreen().getPlayer());
	}

	// ----------------------------------------------------------
	/**
	 * Test the playJingle method.
	 */
	public void testPlayJingle() {
		assertNull(getScreen().getCurrentJingle());
		this.getScreen().playJingle();
		// We tested the condition when the current jingle is not
		// null, but we could not test it because there is not a way
		// to set the jingle without playing the game
	}

	// ----------------------------------------------------------
	/**
	 * Test the setGUIEnabled method.
	 */
	public void testSetGUIEnabled() {
		this.getScreen().setGUIEnabled(true);
		assertTrue(guess.isClickable());
		assertTrue(enterGuess.isClickable());
		assertTrue(replay.isClickable());
	}

	// ----------------------------------------------------------
	/**
	 * Test the replayClicked method. .
	 */
	public void testReplayClicked() {
		testPlayJingle();
	}

	// ----------------------------------------------------------
	/**
	 * Test enterGuessClicked method.
	 */
	public void testEnterGuessClicked() {
		this.getScreen().initialize();
		assertTrue(enterGuess.isClickable());
		// All conditions in this method have been checked.
		// This is the best we could do to test presentScreen.
	}

	// ----------------------------------------------------------
	/**
	 * Test guessEditingDone method.
	 */
	public void testGuessEditingDone() {
		// calls the enterGuessClicked method
		testEnterGuessClicked();
	}
}
