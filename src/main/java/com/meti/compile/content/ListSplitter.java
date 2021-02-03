package com.meti.compile.content;

import com.meti.api.magma.collect.stream.Stream;
import com.meti.api.magma.collect.stream.StreamException;
import com.meti.compile.token.Input;

public abstract class ListSplitter implements Splitter {
	@Override
	public Stream<Input> stream(Input input) {
		return processAll(input)
				.complete()
				.stream()
				.filter(Input::hasContent)
				.map(Input::trim);
	}

	private State processAll(Input input) {
		try {
			return input.stream().fold(new ListState(input), this::process);
		} catch (StreamException e) {
			return new ListState(input);
		}
	}

	protected abstract State process(State state, char c);
}
