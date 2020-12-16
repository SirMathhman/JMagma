package com.meti.api.collect.string;

import com.meti.api.core.Stringable;

public interface StringBuffer extends Stringable {
	StringBuffer Empty = new StringBufferImpl();

	StringBuffer add(char c);

	StringBuffer add(String s);

	class StringBufferImpl implements StringBuffer {
		private final String internalValue;

		private StringBufferImpl() {
			this("");
		}

		private StringBufferImpl(String internalValue) {
			this.internalValue = internalValue;
		}

		@Override
		public StringBuffer add(char c) {
			return new StringBufferImpl(internalValue + c);
		}

		@Override
		public StringBuffer add(String s) {
			return new StringBufferImpl(internalValue + s);
		}

		@Override
		public String asString() {
			return internalValue;
		}
	}
}
