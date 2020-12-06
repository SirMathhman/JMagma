package com.meti.api.collect.string;

import com.meti.api.collect.stream.DelegatedStream;
import com.meti.api.collect.stream.EndOfStreamException;
import com.meti.api.collect.stream.Stream;
import com.meti.api.collect.stream.StreamException;

public class Strings {
	public Strings() {
	}

	public static int length(String this_) {
		return this_.length();
	}

	public static boolean equals(String this_, String other) {
		return compareTo(this_, other) == 0;
	}

	public static String concat(String this_, String other) {
		return this_ + other;
	}

	public static int compareTo(CharSequence this_, CharSequence other) {
		var lengthDelta = this_.length() - other.length();
		var charDelta = 0;
		var minLength = Math.min(this_.length(), other.length());
		for (int i = 0; i < minLength; i++) {
			charDelta += this_.charAt(i) - other.charAt(i);
		}
		return lengthDelta + charDelta;
	}

	public static Stream<Character> stream(String value) {
		return new StringStream(value);
	}

	private static class StringStream extends DelegatedStream<Character> {
		private final int length;
		private final String value;
		private int counter;

		StringStream(String value) {
			this.value = value;
			this.length = value.length();
			this.counter = 0;
		}

		@Override
		protected Character get() throws StreamException {
			if (counter < length) {
				return value.charAt(counter++);
			} else {
				throw EndOfStreamException.EndOfStreamException("No more characters exist in the string.");
			}
		}
	}
}
