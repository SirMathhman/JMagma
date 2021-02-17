package com.meti;

public class EmptyOutput implements Output {
	public static final Output EmptyOutput_ = new EmptyOutput();

	public EmptyOutput() {
	}

	@Override
	public Output appendChar(char c) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Output appendField(Field field) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Output appendOutput(Output output) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Output appendString(String s) {
		throw new UnsupportedOperationException();
	}

	@Override
	public String compute() {
		throw new UnsupportedOperationException();
	}

	@Override
	public Output prependChar(char c) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Output prependString(String s) {
		throw new UnsupportedOperationException();
	}
}
