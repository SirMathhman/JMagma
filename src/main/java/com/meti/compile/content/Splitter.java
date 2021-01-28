package com.meti.compile.content;

import com.meti.api.magma.collect.stream.Stream;

public interface Splitter {
	Stream<String> stream(String content);
}
