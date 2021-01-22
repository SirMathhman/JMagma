package com.meti.compile.content;

import com.meti.api.magma.collect.Stream;
import com.meti.api.magma.collect.StreamException;
import com.meti.api.magma.collect.Streams;

public class BracketSplitter implements Splitter {
	public static final BracketSplitter BracketSplitter_ = new BracketSplitter();

	private BracketSplitter() {
	}

	@Override
	public Stream<String> stream(String content) throws StreamException {
		return Streams.ofIntRange(0, content.length())
				.map(content::charAt)
				.fold(new JavaSplitterState(), this::process)
				.advance()
				.stream();
	}

	private SplitterState process(SplitterState state, char c) {
		if (c == '}' && state.isShallow()) return state.reset()
				.append('}')
				.advance();
		else if (c == ';' && state.isLevel()) return state.advance();
		else if (c == '{') return state.sink().append(c);
		else if (c == '}') return state.surface().append(c);
		else return state.append(c);
	}
}
