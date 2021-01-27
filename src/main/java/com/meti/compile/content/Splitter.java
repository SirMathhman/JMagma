package com.meti.compile.content;

import java.util.stream.Stream;

public interface Splitter {
	Stream<String> stream(String content);
}
