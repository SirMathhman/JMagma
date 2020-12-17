package com.meti.compile;

import java.math.BigInteger;

public class Int implements Node {
	private static final int Base10 = 10;
	private final BigInteger integer;

	private Int(BigInteger integer) {
		this.integer = integer;
	}

	public static Int Int(BigInteger integer) {
		return new Int(integer);
	}

	static Int Int(int val) {
		return Int(BigInteger.valueOf(val));
	}

	@Override
	public String render() {
		return integer.toString(Base10);
	}
}
