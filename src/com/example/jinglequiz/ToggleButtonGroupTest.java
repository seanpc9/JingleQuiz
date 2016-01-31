package com.example.jinglequiz;

import android.content.Context;
import android.test.InstrumentationTestCase;
import android.widget.ToggleButton;

// -------------------------------------------------------------------------
/**
 * Tests the ToggleButtonGroup class for proper functionality.
 *
 * @author Divyansh Gupta (divyg), Sean Crenshaw (seanpc9), Jordan White
 *         (jordanrw)
 * @version 2014.12.01
 */
public class ToggleButtonGroupTest extends InstrumentationTestCase {
	// ~ Fields ................................................................
	private ToggleButtonGroup buttons;
	private ToggleButton butt1;
	private ToggleButton butt2;
	private Context context;

	// ~ Methods ...............................................................
	// ----------------------------------------------------------
	/**
	 * Set up the test scenario for each test method.
	 */
	public void setUp() {
		context = getInstrumentation().getTargetContext();

		buttons = new ToggleButtonGroup();

		butt1 = new ToggleButton(context);
		butt2 = new ToggleButton(context);

		buttons.add(butt1, butt2);
	}

	// ----------------------------------------------------------
	/**
	 * Test method for add()
	 */
	public void testAddAndButtonList() {
		assertEquals(butt1, buttons.getButtonList().get(0));
	}

	// ----------------------------------------------------------
	/**
	 * Test method for {ToggleButtonGroup#remove(android.widget.ToggleButton)}.
	 */
	public void testRemove() {
		buttons.remove(butt1);

		assertEquals(1, buttons.getButtonList().size());
		assertEquals(butt2, buttons.getButtonList().get(0));
	}

	// ----------------------------------------------------------
	/**
	 * Test method for onClick() & getCheckedButton() & getCheckedId()
	 */
	public void testOnClick() {
		buttons.onClick(butt1);
		assertEquals(butt1, buttons.getCheckedButton());

		buttons.onClick(butt1);
		assertNull(buttons.getCheckedButton());
		assertEquals(Integer.MAX_VALUE, buttons.getCheckedId());
	}

	// ----------------------------------------------------------
	/**
	 * Test method for {ToggleButtonGroup#clearChecked()}.
	 */
	public void testClearChecked() {
		buttons.onClick(butt1);
		buttons.onClick(butt2);

		buttons.clearChecked();

		assertNull(buttons.getCheckedButton());
		assertEquals(Integer.MAX_VALUE, buttons.getCheckedId());
	}

	// ----------------------------------------------------------
	/**
	 * Test method for {ToggleButtonGroup#contains(ToggleButtonGroup)}.
	 */
	public void testContains() {
		ToggleButton butt5 = new ToggleButton(context);

		assertTrue(buttons.contains(butt1));
		assertFalse(buttons.contains(butt5));
	}

	// ----------------------------------------------------------
	/**
	 * Test method for isHapticFeedback() & setHapticFeedback()
	 */
	public void testSetHapticFeedback() {
		buttons.setHapticFeedback(true);
		assertTrue(buttons.isHapticFeedback());

		buttons.setHapticFeedback(false);
		assertFalse(buttons.isHapticFeedback());
	}
}
