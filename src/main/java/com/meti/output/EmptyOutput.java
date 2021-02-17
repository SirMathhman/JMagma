package com.meti.output;

import com.meti.token.Field;

public class EmptyOutput implements Output {
	public static final Output EmptyOutput_ = new EmptyOutput();

	public EmptyOutput() {
	}

	public Output appendChar(char c) {
		throw new UnsupportedOperationException();
	}

	public Output appendField(Field field) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Output append(Output output) {
		throw new UnsupportedOperationException();
	}

	public Output appendString(String s) {
		throw new UnsupportedOperationException();
	}

	@Override
	public String compute() {
		throw new UnsupportedOperationException();
	}

	public Output prependChar(char c) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Output prepend(Output output) {
		throw new UnsupportedOperationException();
	}

	public Output prependString(String s) {
		throw new UnsupportedOperationException();
	}

}
