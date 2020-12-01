package com.meti.api.math;

public class StandardMath implements com.meti.api.math.Math {
	public static final Math Math = new StandardMath();

	private StandardMath() {
	}

	@Override
	public int minInt(int first, int second) {
		return java.lang.Math.min(first, second);
	}
}
