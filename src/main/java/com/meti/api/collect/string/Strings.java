package com.meti.api.collect.string;

import com.meti.api.collect.stream.EndOfStreamException;
import com.meti.api.collect.stream.Stream;
import com.meti.api.collect.stream.StreamException;
import com.meti.api.collect.stream.DelegatedStream;

public class Strings {
	public Strings() {
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
				throw new EndOfStreamException("No more characters exist in the string.");
			}
		}
	}
}
