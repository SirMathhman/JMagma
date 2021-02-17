package com.meti;

import java.util.stream.Stream;

public interface Script {
	Stream<String> stream();

	Stream<Script> streamDescendants();
}
