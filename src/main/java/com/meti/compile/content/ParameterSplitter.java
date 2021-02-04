package com.meti.compile.content;

public class ParameterSplitter extends ListSplitter {
	public static final Splitter ParameterSplitter_ = new ParameterSplitter();

	private ParameterSplitter() {
	}

	@Override
	protected State process(State state, char c) {
		if (c == ',' && state.isLevel()) {
			return state.complete();
		} else if (c == '(') {
			return state.sink().advance();
		} else if (c == ')') {
			return state.surface().advance();
		} else {
			return state.advance();
		}
	}
}
