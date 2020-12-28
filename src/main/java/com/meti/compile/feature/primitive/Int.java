package com.meti.compile.feature.primitive;

import com.meti.api.core.None;
import com.meti.api.core.Option;
import com.meti.api.core.Some;
import com.meti.compile.feature.field.Field;
import com.meti.compile.token.Node;

import java.math.BigInteger;
import java.util.Objects;
import java.util.Optional;

public record Int(BigInteger integer) implements Node {
	private static final int Base10 = 10;

	public static Int Int(int val) {
		return new Int(BigInteger.valueOf(val));
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
	public boolean is(Group group) {
		return group == Group.Integer;
	}

	@Override
	public Option<Field> findIdentity() {
		return findIdentity2()
				.map(Some::Some)
				.orElseGet(None::None);
	}

	@Deprecated
	private Optional<Field> findIdentity2() {
		return Optional.empty();
	}
}
