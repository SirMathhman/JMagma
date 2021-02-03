package com.meti.compile.content;

import com.meti.api.magma.collect.stream.Stream;
import com.meti.api.magma.collect.stream.StreamException;
import com.meti.api.magma.collect.stream.Streams;
import com.meti.compile.token.Input;

public abstract class ListSplitter implements Splitter {
	@Override
	public Stream<Input> stream(Input input) {
		return processAll(input.getContent())
				.advance()
				.stream()
				.filter(Input::hasContent)
				.map(Input::trim);
	}

	State processAll(String content) {
		try {
			return Streams.ofIntRange(0, content.length())
					.map(content::charAt)
					.fold(new ListState(), this::process);
		} catch (StreamException e) {
			return new ListState();
		}
	}

	protected abstract State process(State state, char c);
}
