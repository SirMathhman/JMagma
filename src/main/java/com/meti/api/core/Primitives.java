package com.meti.api.core;

import static com.meti.api.core.FormatException.FormatException;

public class Primitives {
	private static final int delta = 'A' - 'a';

	public Primitives() {
	}

	public static char toUpperCase(char c) {
		if (c >= 'a' && c <= 'z') {
			return (char) (c + delta);
		} else {
			return c;
		}
	}

	public static char toLowerCase(char c) {
		if (c >= 'A' && c <= 'Z') {
			return (char) (c - delta);
		} else {
			return c;
		}
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

	public static boolean isWhiteSpace(char c) {
		return c == '\t' || c == '\n' || c == ' ';
	}
}
