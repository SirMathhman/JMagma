package com.meti.compile.content;

public class BracketSplitter extends ListSplitter {
	public static final BracketSplitter BracketSplitter_ = new BracketSplitter();

	private BracketSplitter() {
	}

	@Override
	protected State process(State state, char c) {
		if (c == '}' && state.isShallow()) {
			return state.surface().advance().complete();
		} else if (c == ';' && state.isLevel()) {
			return state.complete();
		} else if (c == '{') {
			return state.sink().advance();
		} else if (c == '}') {
			return state.surface().advance();
		} else {
			return state.advance();
		}
	}
}