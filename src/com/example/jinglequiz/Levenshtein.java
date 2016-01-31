package com.example.jinglequiz;

import java.util.HashMap;

// -------------------------------------------------------------------------
/**
 * An implementation of the Levenshtein distance algorithm.
 *
 * @author Divyansh Gupta (divyg), Sean Crenshaw (seanpc9), Jordan White
 *         (jordanrw)
 * @version 2014.11.03
 */
public class Levenshtein {

	// ~ Fields ..........................................................
	private String str1;
	private String str2;
	private HashMap<String, Integer> map;

	// ~ Constructors ....................................................
	/**
	 * Create a new Levenshtein object.
	 *
	 * @param string1 is a string that will be compared with string2.
	 * @param string2 is a string that will be compared with string1.
	 */
	public Levenshtein(String string1, String string2) {
		str1 = string1;
		str2 = string2;
		map = new HashMap<String, Integer>();
	}

	// ~ Methods .........................................................
	// ----------------------------------------------------------
	/**
	 * Recursively calculates the Levenshtein distance between the two Strings
	 * that belong to this object.
	 *
	 * @param str1Start is the starting point of the first string.
	 * @param str1Len is the length of the first string.
	 * @param str2Start is the starting point of the second string.
	 * @param str2Len is the length of the second string.
	 * @return the string distance.
	 */
	private int distance(int str1Start, int str1Len, int str2Start, int str2Len) {
		String key = str1Start + ", " + str1Len + ", " + str2Start + ", "
		    + str2Len;
		if (map.containsKey(key)) {
			return map.get(key);
		}
		if (str1Len == 0) {
			return str2Len;
		}
		if (str2Len == 0) {
			return str1Len;
		}
		int toAdd = 1;
		if (str1.charAt(str1Len - 1) == str2.charAt(str2Len - 1)) {
			toAdd = 0;
		}
		int toPut = Math.min(
		    distance(str1Start, str1Len - 1, str2Start, str2Len) + 1, Math.min(
		        distance(str1Start, str1Len, str2Start, str2Len - 1) + 1,
		        distance(str1Start, str1Len - 1, str2Start, str2Len - 1)
		            + toAdd));
		map.put(key, toPut);
		return toPut;
	}

	// ----------------------------------------------------------
	/**
	 * Get the distance between the two strings of the object.
	 *
	 * @return the distance between the two Strings.
	 */
	public int distance() {
		return distance(0, str1.length(), 0, str2.length());
	}
}
