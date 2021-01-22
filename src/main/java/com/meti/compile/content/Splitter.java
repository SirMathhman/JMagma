package com.meti.compile.content;

import com.meti.api.magma.collect.Stream;
import com.meti.api.magma.collect.StreamException;

public interface Splitter {
	Stream<String> stream(String content) throws StreamException;
}
