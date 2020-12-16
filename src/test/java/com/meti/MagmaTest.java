package com.meti;

import com.meti.api.core.Equatable;
import com.meti.api.core.Stringable;

import static org.junit.jupiter.api.Assertions.fail;

public class MagmaTest {
	public static <T extends Stringable & Equatable<T>> void assertStringablesEqual(T first, T second) {
		if (!first.equalsTo(second)) {
			fail("expected: " + first.asString() + " but was " + second.asString());
		}
	}
}
