package com.meti;

public class Strings {


	static StringStream stream(String input) {
		return new StringStream(input);
	}

	public Strings() {
	}

	static class StringStream extends AbstractStream<Character> {
		private final String input;
		private int counter = 0;

		private StringStream(String input) {
			this.input = input;
		}

		@Override
		public Character head() throws EndOfStreamException {
			if (counter < input.length()) {
				return input.charAt(counter++);
			} else {
				throw new EndOfStreamException("No more characters left.");
			}
		}
	}
}
