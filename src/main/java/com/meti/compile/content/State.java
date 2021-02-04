package com.meti.compile.content;

import com.meti.api.magma.collect.stream.Stream;
import com.meti.api.magma.collect.string.Stringable;
import com.meti.compile.token.Input;

public interface State extends Equatable<State>, Stringable {
	State advance();

	State complete();

	boolean isAt(int depth);

	boolean isLevel();

	boolean isShallow();

	boolean isStoring(Input input);

	State sink();

	Stream<Input> stream();

	State surface();
}
