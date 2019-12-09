package com.banking.util;

import java.util.Random;

/**
 * Common Util - Util class for common meothods we can implement here.
 * 
 * @author Govindasamy.C
 * @since : 27-11-2019
 */

public class CommonUtil {

	private CommonUtil() {
	}

	/**
	 * calculate the average value by ratings.
	 * 
	 * @param -> list of the Integer ratings.
	 * @return Here returning the response value as double.
	 */
	public static Integer getTransactionNumber() {
		Random r = new Random(System.currentTimeMillis());
		return r.nextInt(100000) * 00001;

	}
}
