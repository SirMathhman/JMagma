package com.meti;

class Stream {
	private final String input;
	private int counter = 0;

	Stream(String input) {
		this.input = input;
	}

	boolean allMatch(F1<Character, Boolean> predicate) {
		while (true) {
			try {
				char apply = head();
				if (!predicate.apply(apply)) {
					return false;
				}
			} catch (EndOfStreamException e) {
				break;
			}
		}
		return true;
	}

	private char head() throws EndOfStreamException {
		if (counter < input.length()) {
			return input.charAt(counter++);
		} else {
			throw new EndOfStreamException("No more characters left.");
		}
	}
}
