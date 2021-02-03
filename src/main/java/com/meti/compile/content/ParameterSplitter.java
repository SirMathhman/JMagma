package com.meti.compile.content;

public class ParameterSplitter extends ListSplitter {
	public static final Splitter ParameterSplitter_ = new ParameterSplitter();

	private ParameterSplitter() {
	}

	@Override
	protected State process(State state, char c) {
		if (c == ',' && state.isLevel()) {
			return state.advance();
		} else if (c == '(') {
			return state.sink().append(c);
		} else if (c == ')') {
			return state.surface().append(c);
		} else {
			return state.append(c);
		}
	}
}
