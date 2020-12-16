package com.meti.api.string;

import com.meti.api.core.Primitives;
import com.meti.api.memory.Allocator;

import static com.meti.api.string.ArrayStringBuffer.ArrayStringBuffer;

public class Strings {
	private Strings() {
	}

	public static String fromInt(int value, Allocator allocator) {
		return fromInt(value, 10, allocator);
	}

	public static String fromInt(int value, int radix, Allocator allocator) {
		if (value >= 0 && value < radix) {
			return ArrayStringBuffer(allocator)
					.addChar(Primitives.asAlphaNumeric(value))
					.asString();
		} else if (value < 0) {
			return ArrayStringBuffer(allocator)
					.addChar('-')
					.addString(fromInt(-value, radix, allocator))
					.asString();
		} else {
			var parent = value / radix;
			var digit = value % radix;
			var parentString = fromInt(parent, radix, allocator);
			return ArrayStringBuffer(allocator)
					.addString(parentString)
					.addChar(Primitives.asAlphaNumeric(digit))
					.asString();
		}
	}
}
