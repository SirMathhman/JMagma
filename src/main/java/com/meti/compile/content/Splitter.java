package com.meti.compile.content;

import com.meti.api.java.collect.stream.Stream;

public interface Splitter {
	Stream<String> stream(String content);
}
