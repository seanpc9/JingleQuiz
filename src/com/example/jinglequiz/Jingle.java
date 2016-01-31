package com.example.jinglequiz;

import java.util.List;
import android.annotation.SuppressLint;
import android.content.Context;

// -------------------------------------------------------------------------
/**
 * Represents a Jingle, or marketing slogan sound, for a company. Recommended to
 * place audio in the res/raw folder of the application. Contains the audio
 * (mp3Name) and a list of acceptable companyNames to represent the company that
 * produces this Jingle.
 *
 * @author Divyansh Gupta (divyg), Sean Crenshaw (seanpc9), Jordan White
 *         (jordanrw)
 * @version 2014.12.01
 */
public class Jingle {
	// ~ Fields ................................................................
	private String mp3Name;
	private List<String> companyNames;

	// ~ Constructors ..........................................................
	// ----------------------------------------------------------
	/**
	 * Create a new Jingle object.
	 *
	 * @param newMp3Name is the name of the mp3 file of the audio in the res/raw
	 *            folder that should play when this Jingle is called.
	 * @param newCompany is the list of acceptable company names for the company
	 *            that produces this Jingle.
	 */
	public Jingle(String newMp3Name, List<String> newCompany) {
		mp3Name = newMp3Name;
		companyNames = newCompany;
	}

	// ~ Methods ...............................................................
	// ----------------------------------------------------------
	/**
	 * Get the name of the sound file that is associated with this Jingle.
	 *
	 * @return the name of the sound file that has the audio for this Jingle.
	 */
	public String mp3Name() {
		return mp3Name;
	}

	// ----------------------------------------------------------
	/**
	 * Get the list of company and brand names that are acceptable for naming
	 * the company that produces this Jingle.
	 *
	 * @return the list of company and brand names that are acceptable for
	 *         naming the company that produces this Jingle.
	 */
	public List<String> company() {
		return companyNames;
	}

	// ----------------------------------------------------------
	/**
	 * Get the path for the Jingle, if located in the res/raw folder.
	 *
	 * @param context is the application context in which to look for the
	 *            resource.
	 * @return the path for the Jingle in the res/raw folder.
	 */
	public String getRawResourcePath(Context context) {
		String path = "android.resource://" + context.getPackageName()
		    + "/raw/" + this.mp3Name();
		return path;
	}

	// ----------------------------------------------------------
	/**
	 * Finds the smallest Levenshtein distance between a guessed Jingle name and
	 * the list of accepted company names for the Jingle.
	 *
	 * @param guessed is the Jingle company name that was guessed.
	 * @return the smallest Levenshtein distance between the guessed company and
	 *         the list of accepted company names for the Jingle.
	 */
	@SuppressLint("DefaultLocale")
	public int verifyDistance(String guessed) {
		int distance = Integer.MAX_VALUE;
		for (String name : companyNames) {
			Levenshtein leven = new Levenshtein(name.toLowerCase(),
			    guessed.toLowerCase());
			int currDistance = leven.distance();
			if (currDistance < distance) {
				distance = currDistance;
			}
		}
		return distance;
	}

	// ----------------------------------------------------------
	/**
	 * Overrides the equals(Object o) method in Object. Checks for equality of
	 * Jingles using mp3Name and companyName.
	 *
	 * @return true if Jingle objects have the same mp3Name and companyName.
	 *         Otherwise, return false.
	 */
	@Override
	public boolean equals(Object o) {
		if (o instanceof Jingle) {
			Jingle jingle = (Jingle) o;
			if (jingle.mp3Name.equals(mp3Name)
			    && jingle.companyNames.get(0).equals(companyNames.get(0))) {
				return true;
			}
		}
		return false;
	}

	// ----------------------------------------------------------
	/**
	 * As is recommended when overriding equals(Object o), also override
	 * hashCode() to ensure that when equals() returns true, hashCode() of the
	 * two objects are also equal.
	 *
	 * @return the hashCode for the Jingle object, and all equal Jingle Objects.
	 */
	@Override
	public int hashCode() {
		return mp3Name.hashCode() + companyNames.get(0).hashCode();
	}
}
