package com.meti.compile.content;

import com.meti.api.magma.collect.stream.Stream;
import com.meti.api.magma.collect.stream.StreamException;
import com.meti.compile.token.Input;

import static com.meti.compile.content.ListStates.ListState;

public abstract class ListSplitter implements Splitter {
	@Override
	public Stream<Input> stream(Input input) {
		return processAll(input, createState(input))
				.complete()
				.stream()
				.filter(Input::hasContent)
				.map(Input::trim);
	}

	private State processAll(Input input, State state) {
		try {
			return input.stream().fold(state, this::process);
		} catch (StreamException e) {
			return ListStates.EmptyListState;
		}
	}

	private State createState(Input input) {
		return ListState().of(input).complete();
	}

	protected abstract State process(State state, char c);
}
