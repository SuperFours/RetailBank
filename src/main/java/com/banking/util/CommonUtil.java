package com.banking.util;

import java.util.Random;

/**
 * @description CommonUtil - get the common static methods implemeneted in the
 *              util class are generated and get the transaction number.
 * 
 * @author Govindasamy.C
 * @since : 27-11-2019
 */

public class CommonUtil {

	private CommonUtil() {
	}

	/**
	 * @description get the transaction number by generated new value.
	 * @return the Integer value by the generated new transaction number.
	 */
	public static Integer getTransactionNumber() {
		Random r = new Random(System.currentTimeMillis());
		return r.nextInt(100000) * 00001;

	}
}
