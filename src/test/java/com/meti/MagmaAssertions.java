package com.meti;

import com.meti.api.magma.collect.IndexException;
import com.meti.api.magma.collect.list.List;
import com.meti.api.magma.collect.string.Stringable;
import com.meti.compile.content.Equatable;

import static org.junit.jupiter.api.Assertions.fail;

public class MagmaAssertions {
	public static <T extends Equatable<T> & Stringable> void assertSequenceEquals(List<T> actual, List<T> expected) {
		var expectedSize = expected.size();
		var actualSize = actual.size();
		if (expectedSize != actualSize) {
			fail("\n\texpected a size of: %d\n\t,but was a size of: %d".formatted(expectedSize, actualSize));
		}
		for (int i = 0; i < expectedSize; i++) {
			try {
				var expectedValue = expected.apply(i);
				try {
					var actualValue = actual.apply(i);
					if (!expectedValue.equalsTo(actualValue)) {
						fail("Contents differ at index %d\n\texpected: %s\n\t,but was: %s".formatted(i, expectedValue.asString(), actualValue.asString()));
					}
				} catch (IndexException e) {
					fail("Failed to access actual value at index %d".formatted(i), e);
				}
			} catch (IndexException e) {
				fail("Failed to access expected value at index %d".formatted(i), e);
			}
		}
	}

	public static <T extends Stringable & Equatable<T>> void assertEquals(T actual, T expected) {
		if (!actual.equalsTo(expected)) {
			fail("\n\texpected: %s\n\t,but was: %s".formatted(expected.asString(), actual.asString()));
		}
	}
}
