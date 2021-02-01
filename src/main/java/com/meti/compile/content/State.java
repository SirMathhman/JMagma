package com.meti.compile.content;

import com.meti.api.magma.collect.stream.Stream;

public interface State extends Equatable<State> {
	State advance();

	State append(char c);

	boolean isAt(int depth);

	boolean isLevel();

	boolean isShallow();

	boolean isStoring(String value);

	State reset();

	State sink();

	Stream<String> stream();

	State surface();
}
