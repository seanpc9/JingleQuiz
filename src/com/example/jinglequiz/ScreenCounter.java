package com.example.jinglequiz;

import android.content.res.TypedArray;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

// -------------------------------------------------------------------------
/**
 * Subclass of TextView that is designed to display a count on a screen in the
 * format: "count/end" where count is the incrementing count of the
 * ScreenCounter, and end is the value to stop incrementing at. Use in
 * conjunction with declaration of ScreenCounter in res/values/attrs.xml. Set
 * startCount and endCount attributes in layout XML for this ScreenCounter.
 *
 * @author Divyansh Gupta (divyg), Sean Crenshaw (seanpc9), Jordan White
 *         (jordanrw)
 * @version 2014.12.01
 */
public class ScreenCounter extends TextView {
	// ~ Fields ................................................................
	private int end;
	private int current;

	// ~ Constructors ..........................................................
	// ----------------------------------------------------------
	/**
	 * Create a new ScreenCounter object.
	 *
	 * @param context is the Context of the Screen that will display this
	 *            ScreenCounter.
	 * @param attrs are the attributes passed in. Supports all TextView
	 *            attributes, as well as new startCount and endCount attributes.
	 *            If not defined, default for startCount is 0 and default for
	 *            endCount is 10.
	 */
	public ScreenCounter(Context context, AttributeSet attrs) {
		super(context, attrs);
		TypedArray a = context.getTheme().obtainStyledAttributes(attrs,
		    R.styleable.ScreenCounter, 0, 0);
		try {
			current = a.getInteger(R.styleable.ScreenCounter_startCount, 0);
			end = a.getInteger(R.styleable.ScreenCounter_endCount, 10);
		} finally {
			a.recycle();
		}
	}

	// ~ Methods ...............................................................
	// ----------------------------------------------------------
	/**
	 * Increment the current count of the Counter up by one, and update the View
	 * to show this.
	 */
	public void incrementUp() {
		if (current < end) {
			current++;
			this.setText();
		}
	}

	// ----------------------------------------------------------
	/**
	 * Increment the current count of the Counter down by one, and update the
	 * View to show this.
	 */
	public void incrementDown() {
		current--;
		this.setText();
	}

	// ----------------------------------------------------------
	/**
	 * Update the View of the ScreenCounter to show the current count.
	 */
	public void setText() {
		String text = current + "/" + end;
		this.setText(text);
	}

	// ----------------------------------------------------------
	/**
	 * Set the new endCount for the ScreenCounter (where the Counter will stop
	 * incrementing up).
	 *
	 * @param newEnd is where the ScreenCounter where will stop incrementing up.
	 */
	public void setEndCount(int newEnd) {
		end = newEnd;
		this.setText();
	}

	// ----------------------------------------------------------
	/**
	 * Set the count for the ScreenCounter to display.
	 *
	 * @param newCurrent is the count the ScreenCounter will display.
	 */
	public void setCurrent(int newCurrent) {
		current = newCurrent;
		this.setText();
	}
}
