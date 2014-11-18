package com.example.jinglequiz;

import android.widget.Button;

import sofia.graphics.ShapeView;

import sofia.app.ShapeScreen;

// -------------------------------------------------------------------------
/**
 * The backend for the WelcomeScreen.
 *
 * @author Divyansh Gupta (divyg)
 * @version Nov 17, 2014
 */

public class WelcomeScreen extends ShapeScreen {
<<<<<<< HEAD
    	// Fields
    	private ShapeView shapeView;
    	private Button easy;
    	private Button medium;
    	private Button hard;
    	private Button start;
=======
    // Fields
    private Button easy;
    private Button medium;
    private Button hard;
    private Button start;

    // ----------------------------------------------------------
    /**
     * Creates the WelcomeScreen on the screen of the device.
     */
    @Override
    public void initialize() {
	// Leave empty for now.
    }

    // ----------------------------------------------------------
    /**
     * Creates the sets the parameters for an easy game.
     */
    public void easyClicked() {

    }

    // ----------------------------------------------------------
    /**
     * Creates the sets the parameters for a medium game.
     */
    public void mediumClicked() {

    }
>>>>>>> 9ef2a08e65a8cdf3044cef98c075166a68ce47c4

    // ----------------------------------------------------------
    /**
     * Creates the sets the parameters for a hard game.
     */
    public void hardClicked() {

    }


    // ----------------------------------------------------------
    /**
     * Redirects to the game screen.
     */
    public void startClicked() {
	this.presentScreen(GameScreen.class, 2);
    }
}
