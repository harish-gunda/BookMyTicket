package application;

public class Util {

	/** Return true if the card number is valid */
	public static boolean isValid(long number) {
		if ((getSize(number) >= 13 && getSize(number) <= 16) && isMatchWithAnyPrefix(number)) {
			int sumOfDoubleEvenPlace = sumOfDoubleEvenPlace(number);
			int sumOfOddPlace = sumOfOddPlace(number);
			return (sumOfDoubleEvenPlace + sumOfOddPlace) % 10 == 0;
		}
		return false;
	}

	public static boolean isMatchWithAnyPrefix(long number) {
		int vals[] = new int[] { 4, 5, 37, 6 };
		for (int i = 0; i < 4; i++) {
			if (prefixMatched(number, vals[i])) {
				return true;
			}
		}
		return false;
	}

	/** Get the result from Step 2 */
	public static int sumOfDoubleEvenPlace(long number) {
		int sumOfDoubleEvenVals = 0;
		int size = getSize(number);
		String cardAsString = String.valueOf(number);

		for (int i = size - 2; i >= 0; i = i - 2) {
			int val = Integer.parseInt(cardAsString.charAt(i) + "");
			sumOfDoubleEvenVals = sumOfDoubleEvenVals + getDigit(val * 2);
		}
		return sumOfDoubleEvenVals;
	}

	/**
	 * Return this number if it is a single digit, otherwise, return the sum of the
	 * two digits
	 */
	public static int getDigit(int number) {
		if (number < 9) {
			return number;
		} else {
			return (number / 10) + (number % 10);
		}
	}

	/** Return sum of odd place digits in number */
	public static int sumOfOddPlace(long number) {
		int sumOfOddVals = 0;
		int size = getSize(number);
		String cardAsString = String.valueOf(number);

		for (int i = size - 1; i >= 0; i = i - 2) {
			int val = Integer.parseInt(cardAsString.charAt(i) + "");
			sumOfOddVals = sumOfOddVals + val;
		}
		return sumOfOddVals;
	}

	/** Return true if the digit d is a prefix for number */
	public static boolean prefixMatched(long number, int d) {
		if (getPrefix(number, getSize(d)) == d) {
			return true;
		} else {
			return false;
		}
	}

	/** Return the number of digits in d */
	public static int getSize(long d) {
		return String.valueOf(d).length();
	}

	/**
	 * Return the first k number of digits from number. If the number of digits in
	 * number is less than k, return number.
	 */
	public static long getPrefix(long number, int k) {
		int size = getSize(number);
		if (size > k) {
			String cardPrefix = String.valueOf(number).substring(0, k);
			return Long.parseLong(cardPrefix);
		}
		return number;
	}
}
