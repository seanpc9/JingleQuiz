package com.example.jinglequiz;

import android.view.HapticFeedbackConstants;
import android.view.View;
import android.widget.ToggleButton;
import android.view.View.OnClickListener;
import java.util.ArrayList;

// -------------------------------------------------------------------------
/**
 * Represents a group of ToggleButtons where only one button can be toggled at
 * one time. Functions similarly to RadioButtonGroup.
 *
 * @author Divyansh Gupta (divyg), Sean Crenshaw (seanpc9), Jordan White
 *         (jordanrw)
 * @version 2014.12.01
 */
public class ToggleButtonGroup implements OnClickListener {
	// ~ Fields ................................................................
	private ArrayList<ToggleButton> tButtons;
	private ToggleButton checkedButton;
	private int checkedButtonId;
	private boolean hapticFeedback;

	// ~ Constructors ..........................................................
	// ----------------------------------------------------------
	/**
	 * Creates a new ToggleButtonGroup object.
	 */
	public ToggleButtonGroup() {
		tButtons = new ArrayList<ToggleButton>();
		setHapticFeedback(false);
		checkedButtonId = Integer.MAX_VALUE;
	}

	// ~ Methods ...............................................................
	// ----------------------------------------------------------
	/**
	 * Adds a button to the ToggleButtonGroup.
	 *
	 * @param buttons are the ToggleButtons to add to the ToggleButtonGroup.
	 */
	public void add(ToggleButton... buttons) {
		for (ToggleButton button : buttons) {
			button.setOnClickListener(this);
			tButtons.add(button);
		}
	}

	// ----------------------------------------------------------
	/**
	 * Removes a button from the ToggleButtonGroup.
	 *
	 * @param rButton is the ToggleButton to remove from the group.
	 */
	public void remove(ToggleButton rButton) {
		if (rButton.equals(checkedButton)) {
			checkedButton = null;
			checkedButtonId = Integer.MAX_VALUE;
		}
		for (int i = 0; i < tButtons.size(); i++) {
			ToggleButton button = tButtons.get(i);
			if (button.equals(rButton)) {
				button.setOnClickListener(null);
				tButtons.remove(i);
				break;
			}
		}
	}

	// ----------------------------------------------------------
	/**
	 * Reacts to a click on one of the buttons in the group. Makes sure only one
	 * button is clicked at a time in the group.
	 *
	 * @param buttonClicked is the button that is clicked to trigger the onClick
	 *            method.
	 */
	public void onClick(View buttonClicked) {
		if (checkedButton != null && checkedButton.equals(buttonClicked)) {
			checkedButton = null;
			checkedButtonId = Integer.MAX_VALUE;
		} else {
			for (ToggleButton button : tButtons) {
				if (!button.equals(buttonClicked)) {
					button.setChecked(false);
				}
			}
			checkedButton = (ToggleButton) buttonClicked;
			checkedButtonId = checkedButton.getId();
		}
		this.hapticFeedBack();
	}

	// ----------------------------------------------------------
	/**
	 * If hapticFeedback is enabled, performs Haptic Feedback if button in Group
	 * is clicked.
	 */
	public void hapticFeedBack() {
		if (checkedButton != null && hapticFeedback) {
			checkedButton
			    .performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
		}
	}

	// ----------------------------------------------------------
	/**
	 * Gets the checked ToggleButton from the ToggleButtonGroup.
	 *
	 * @return the checked ToggleButton from the ToggleButtonGroup
	 */
	public ToggleButton getCheckedButton() {
		return checkedButton;
	}

	// ----------------------------------------------------------
	/**
	 * Sets setChecked() to be false for all buttons in ToggleButtonGroup.
	 */
	public void clearChecked() {
		for (ToggleButton button : tButtons) {
			button.setChecked(false);
		}
		checkedButton = null;
		checkedButtonId = Integer.MAX_VALUE;
	}

	// ----------------------------------------------------------
	/**
	 * Checks whether or not ToggleButtonGroup contains provided button
	 * parameter.
	 *
	 * @param button is the ToggleButton to check for in the ToggleButtonGroup.
	 * @return whether or not the ToggleButtonGroup contains the given button or
	 *         not.
	 */
	public boolean contains(ToggleButton button) {
		for (ToggleButton b : tButtons) {
			if (b.equals(button)) {
				return true;
			}
		}
		return false;
	}

	// ----------------------------------------------------------
	/**
	 * Get whether HapticFeedback is enabled or not.
	 *
	 * @return the hapticFeedback boolean value.
	 */
	public boolean isHapticFeedback() {
		return hapticFeedback;
	}

	// ----------------------------------------------------------
	/**
	 * Set whether or not HapticFeedback should occur every time a button in the
	 * Group is clicked.
	 *
	 * @param hapticFeeback is whether or not HapticFeedback should be enabled
	 *            every time a button in the Group is clicked. Default is false.
	 */
	public void setHapticFeedback(boolean hapticFeeback) {
		this.hapticFeedback = hapticFeeback;
	}

	// ----------------------------------------------------------
	/**
	 * Get the integer ID for the checked ToggleButton in the group, if there is
	 * one. Else, return Integer.MAX_VALUE.
	 *
	 * @return the ID for the checked ToggleButton in the group.
	 */
	public int getCheckedId() {
		return checkedButtonId;
	}

	// ----------------------------------------------------------
	/**
	 * Get the list of Buttons within the ToggleButtonGroup.
	 *
	 * @return the buttons within the ToggleButtonGroup.
	 */
	public ArrayList<ToggleButton> getButtonList() {
		return tButtons;
	}
}
