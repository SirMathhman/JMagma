package com.meti.api.core;

public class Characters {
	private Characters() {
	}

	public static boolean isWhitespace(char c) {
		return c == '\t' || c == '\n' || c == ' ';
	}

	public static long hash(char c) {
		return c;
	}
}
