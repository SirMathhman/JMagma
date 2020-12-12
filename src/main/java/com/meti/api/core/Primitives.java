package com.meti.api.core;

import static com.meti.api.core.FormatException.FormatException;

public class Primitives {
	public Primitives() {
	}

	public static int asDigit(char c) throws FormatException {
		if (isDigit(c)) {
			return c - '0';
		} else {
			throw FormatException("'" + c + "' isn't a digit.");
		}
	}

	public static boolean isDigit(char c) {
		return c >= '0' && c <= '9';
	}

	public static int comparingInts(int first, int second) {
		return first - second;
	}

	public static boolean isWhitespace(char c) {
		return c == '\t' || c == '\n' || c == ' ';
	}

	public static long hash(char c) {
		return c;
	}
}
