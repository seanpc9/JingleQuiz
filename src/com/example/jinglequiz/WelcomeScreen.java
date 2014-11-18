package com.example.jinglequiz;

import sofia.graphics.RectangleShape;
import sofia.graphics.ShapeView;

import sofia.app.ShapeScreen;

// -------------------------------------------------------------------------
/**
 * Write a one-sentence summary of your class here. Follow it with additional
 * details about its purpose, what abstraction it represents, and how to use it.
 *
 * @author Divyansh Gupta (divyg)
 * @version Nov 17, 2014
 */

public class WelcomeScreen extends ShapeScreen {

	// ----------------------------------------------------------
	/**
	 * Creates the WelcomeScreen on the screen of the device.
	 */
	@Override
	public void initialize() {
		addLogo();
	}

	// ----------------------------------------------------------
	/**
	 * Add Logo to the HomeScreen.
	 */
	private void addLogo() {
		ShapeView shapeView = (ShapeView) this.findViewById(R.id.shapeView);
		RectangleShape logoShape = new RectangleShape(0, 0, getWidth(),
		    getHeight());
		logoShape.setImage("ic_launcher");
		shapeView.add(logoShape);
	}
}
