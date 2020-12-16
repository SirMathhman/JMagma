package com.meti.api.core;

public class Primitives {
	private Primitives() {
	}

	public static char asAlphaNumeric(int digit) {
		var index = digit % 36;
		return (char) (index < 10 ? '0' + index : 'A' + (index - 10));
	}
}
