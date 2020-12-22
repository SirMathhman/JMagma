package com.meti.compile;

import java.util.stream.Stream;

public interface Script {
	Stream<String> streamAll();

	Stream<String> streamParents();

	String name();
}