package com.meti.api.collect;

import com.meti.api.math.StandardMath;

import static com.meti.api.collect.ArrayList.Range;
import static com.meti.api.collect.IndexException.IndexException;
import static com.meti.api.collect.ListStream.ListStreams;

public class Strings {
	public static final Strings Strings = new Strings();
	private static final Builder Builder = new Builder();

	private Strings() {
	}

	public String slice(String value, int lowerBound, int upperBound) throws IndexException {
		var length = value.length();
		if (lowerBound < 0) throw IndexException("Lower bound cannot be negative.");
		if (lowerBound >= length) throw IndexException("Lower bound cannot be greater than or equal to length.");
		if (upperBound < 0) throw IndexException("Upper bound cannot be negative.");
		if (upperBound >= length) throw IndexException("Upper bound cannot be greater than or equal to length.");
		if (upperBound < lowerBound) throw IndexException("Upper bound cannot be less than the lower bound.");
		if (upperBound == lowerBound) return "";

		try {
			return ListStreams.ofList(Range(lowerBound, upperBound))
					.map(value::charAt)
					.fold(Builder, (builder, c) -> builder.appendCharacter(c))
					.toString();
		} catch (StreamException e) {
			return "";
		}
	}

	public int compareTo(String first, String second) {
		var minLength = StandardMath.Math.minInt(first.length(), second.length());
		var delta = 0;
		for (int i = 0; i < minLength; i++) {
			var firstChar = first.charAt(i);
			var secondChar = second.charAt(i);
			delta = firstChar - secondChar;
		}
		return delta;
	}

	public boolean equals(String first, String second) {
		return compareTo(first, second) == 0;
	}

	public int indexOf(String value, String delimiter) {
		var length = value.length();
		var delimiterLength = delimiter.length();
		var lastBound = length - delimiterLength;
		for (int i = 0; i < lastBound; i++) {
			String slice;
			try {
				slice = slice(value, i, i + delimiterLength);
			} catch (IndexException e) {
				slice = "";
			}
			if (slice.equals(delimiter)) {
				return i;
			}
		}
		return -1;
	}

	public boolean has(String value, String delimiter) {
		return indexOf(value, delimiter) != -1;
	}

	private String formatNext(String format, Object arg) {
		var objectIndex = indexOf(format, "%o");
		if (objectIndex == -1) return format;
		try {
			var before = slice(format, 0, objectIndex);
			var after = slice(format, objectIndex + 2, format.length());
			return before + arg.toString() + after;
		} catch (IndexException e) {
			return format;
		}
	}

	public String format(String format, Object... args) {
		if (args.length == 0) {
			return format;
		} else {
			var first = args[0];
			var nextFormat = formatNext(format, first);
			Object[] copy = new Object[args.length - 1];
			System.arraycopy(args, 1, copy, 0, copy.length);
			return format(nextFormat, copy);
		}
	}

	static class Builder {
		private final String internal;

		Builder() {
			this("");
		}

		Builder(String internal) {
			this.internal = internal;
		}

		Builder appendCharacter(char value) {
			return new Builder(internal + value);
		}

		Builder appendString(String value) {
			return new Builder(internal + value);
		}

		@Override
		public String toString() {
			return internal;
		}
	}
}
