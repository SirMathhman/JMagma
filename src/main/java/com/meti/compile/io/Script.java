package com.meti.compile.io;

import java.util.stream.Stream;

public interface Script {
	Stream<String> stream();

	Stream<Script> streamAll();
}
