package com.meti.compile.content;

import com.meti.api.java.collect.stream.Stream;

public class BracketSplitter implements Splitter {
	public static final Splitter BracketSplitter_ = new BracketSplitter();

	BracketSplitter() {
	}

	@Override
	public Stream<String> stream(String content) {
		return processAll(content)
				.advance()
				.stream()
				.filter(s -> !s.isBlank())
				.map(String::trim);
	}

	State processAll(String content) {
		State current = new JavaState();
		for (int i = 0; i < content.length(); i++) {
			current = process(current, content.charAt(i));
		}
		return current;
	}

	State process(State state, char c) {
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