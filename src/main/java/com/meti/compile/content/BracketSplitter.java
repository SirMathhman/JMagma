package com.meti.compile.content;

public class BracketSplitter extends ListSplitter {
	public static final Splitter BracketSplitter_ = new BracketSplitter();

	BracketSplitter() {
	}

	@Override
	protected State process(State state, char c) {
		if (c == '}' && state.isShallow()) {
			return state.reset().append('}').advance();
		} else if (c == ';' && state.isLevel()) {
			return state.advance();
		} else if (c == '{') {
			return state.sink().append(c);
		} else if (c == '}') {
			return state.surface().append(c);
		} else {
			return state.append(c);
		}
	}
}