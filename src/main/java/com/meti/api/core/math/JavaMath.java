package com.meti.api.core.math;

public class JavaMath implements Math {
	public static final JavaMath Math_ = new JavaMath();

	@Override
	public int maxInt(int first, int second) {
		return java.lang.Math.max(first, second);
	}
}
