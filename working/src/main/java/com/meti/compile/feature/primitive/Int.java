package com.meti.compile.feature.primitive;

import com.meti.compile.feature.LeafNode;

import java.math.BigInteger;
import java.util.Objects;
import java.util.Optional;

public class Int implements LeafNode {
	private static final int Base10 = 10;
	private final BigInteger integer;

	private Int(BigInteger integer) {
		this.integer = integer;
	}

	public static Int Int(BigInteger integer) {
		return new Int(integer);
	}

	public static Int Int(int val) {
		return Int(BigInteger.valueOf(val));
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

	@Override
	public String render() {
		return integer.toString(Base10);
	}

	@Override
	public Optional<String> findContent() {
		return Optional.empty();
	}

	@Override
	public boolean is(Group group) {
		return false;
	}
}
