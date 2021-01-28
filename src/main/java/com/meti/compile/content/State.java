package com.meti.compile.content;

import com.meti.api.java.collect.stream.Stream;

public interface State {
	State advance();

	State append(char c);

	boolean isLevel();

	boolean isShallow();

	State reset();

	State sink();

	Stream<String> stream();

	State surface();
}
