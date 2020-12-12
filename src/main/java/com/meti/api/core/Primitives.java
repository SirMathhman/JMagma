package com.meti.api.core;

public class Primitives {
	public Primitives() {
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
