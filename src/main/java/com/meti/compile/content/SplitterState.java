package com.meti.compile.content;

import com.meti.api.magma.collect.CollectionException;
import com.meti.api.magma.collect.Stream;

public interface SplitterState {
	SplitterState advance() throws CollectionException;

	SplitterState append(char c);

	boolean isLevel();

	boolean isShallow();

	SplitterState reset();

	SplitterState sink();

	Stream<String> stream();

	SplitterState surface();
}
