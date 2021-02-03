package com.meti.compile.content;

import com.meti.api.magma.collect.stream.Stream;
import com.meti.compile.token.Input;

public interface State extends Equatable<State> {
	State complete();

	State advance();

	boolean isAt(int depth);

	boolean isLevel();

	boolean isShallow();

	boolean isStoring(String value);

	State reset();

	State sink();

	Stream<Input> stream();

	State surface();
}
