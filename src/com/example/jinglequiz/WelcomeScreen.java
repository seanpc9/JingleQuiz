package com.example.jinglequiz;

import com.example.jinglequiz.JingleQuiz.GameDifficulty;
import android.annotation.SuppressLint;
import android.widget.ToggleButton;
import sofia.graphics.RectangleShape;
import sofia.graphics.ShapeView;
import sofia.app.ShapeScreen;

// -------------------------------------------------------------------------
/**
 * This is the first Activity Screen that user will see when opening JingleQuiz
 * application. Allows user to select a difficulty level for their next game,
 * start a new game, and view high scores from previous games they have played.
 *
 * @author Divyansh Gupta (divyg), Sean Crenshaw (seanpc9), Jordan White
 *         (jordanrw)
 * @version 2014.11.17
 */
public class WelcomeScreen extends ShapeScreen {

	// ~ Fields ................................................................
	private ToggleButtonGroup tButtons;
	private GameDifficulty difficulty;

	// ~ Methods ...............................................................
	// ----------------------------------------------------------
	/**
	 * Creates the WelcomeScreen on the screen of the device.
	 */
	@Override
	public void initialize() {
		this.addLogo();
		this.initializeButtons();
	}

	// ----------------------------------------------------------
	/**
	 * Sets up the Buttons on the WelcomeScreen.
	 */
	private void initializeButtons() {
		tButtons = new ToggleButtonGroup();
		ToggleButton easy = (ToggleButton) this.findViewById(R.id.easy);
		ToggleButton medium = (ToggleButton) this.findViewById(R.id.medium);
		ToggleButton hard = (ToggleButton) this.findViewById(R.id.hard);
		tButtons.add(easy, medium, hard);
		tButtons.setHapticFeedback(true);
	}

	// ----------------------------------------------------------
	/**
	 * Add JingleQuiz Image to the HomeScreen.
	 */
	private void addLogo() {
		ShapeView shapeView = (ShapeView) this.findViewById(R.id.shapeView);
		RectangleShape logoShape = new RectangleShape(0, 0, getWidth(),
		    getHeight());
		logoShape.setImage("ic_launcher");
		shapeView.add(logoShape);
	}

	// ----------------------------------------------------------
	/**
	 * Redirects to the game screen, passing in the user defined difficulty
	 * level for their JingleQuiz game.
	 */
	@SuppressLint("DefaultLocale")
	public void startClicked() {
		difficulty = GameDifficulty.MEDIUM;
		if (tButtons.getCheckedButton() != null) {
			String strChecked = tButtons.getCheckedButton().getTextOn()
			    .toString().toLowerCase();
			if (strChecked.equals("easy")) {
				difficulty = GameDifficulty.EASY;
			} else if (strChecked.equals("medium")) {
				difficulty = GameDifficulty.MEDIUM;
			} else if (strChecked.equals("hard")) {
				difficulty = GameDifficulty.HARD;
			}
		}
		this.presentScreen(GameScreen.class, difficulty);
	}

	// ----------------------------------------------------------
	/**
	 * Returns the game difficulty.
	 *
	 * @return game difficulty
	 */
	public GameDifficulty getDifficulty() {
		return difficulty;
	}

	// ----------------------------------------------------------
	/**
	 * Redirects to the scores screen when scores button is clicked.
	 */
	public void scoresClicked() {
		this.presentScreen(ScoreScreen.class);
	}
}
