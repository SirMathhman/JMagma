package com.meti.api.core;

public class Primitives {
	public Primitives() {
	}

	public static String valueOfInt(int value) {
		if (value == 0) return "0";
		if (value < 0) return "-" + valueOfInt(-value);
		else {
			var parent = value / 10;
			var parentString = (parent == 0) ? "" : valueOfInt(parent);
			var digit = value % 10;
			var digitChar = '0' + digit;
			return parentString + (char) digitChar;
		}
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
