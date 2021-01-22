package com.meti.compile.io;

import com.meti.api.magma.collect.Stream;
import com.meti.api.magma.collect.StreamException;

public interface Source {
	String apply(int index);

	String createName();

	int size();

	Stream<String> streamParent() throws StreamException;
}
