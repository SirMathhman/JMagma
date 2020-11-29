package com.meti.api.io;

public class StringInStream implements InStream {
	private final String value;
	private int counter = 0;

	public StringInStream(String value) {
		this.value = value;
	}

	@Override
	public int read() {
		if (counter < value.length()) {
			int c = value.charAt(counter);
			counter++;
			return c;
		}
		return EndOfFile;
	}

	@Override
	public void close() {
	}
}
