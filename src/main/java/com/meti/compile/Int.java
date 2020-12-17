package com.meti.compile;

import java.math.BigInteger;
import java.util.Objects;

public class Int implements Node {
	private static final int Base10 = 10;
	private final BigInteger integer;

	private Int(BigInteger integer) {
		this.integer = integer;
	}

	public static Int Int(BigInteger integer) {
		return new Int(integer);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Int anInt = (Int) o;
		return Objects.equals(integer, anInt.integer);
	}

	@Override
	public int hashCode() {
		return Objects.hash(integer);
	}

	public static Int Int(int val) {
		return Int(BigInteger.valueOf(val));
	}

	@Override
	public String render() {
		return integer.toString(Base10);
	}
}
