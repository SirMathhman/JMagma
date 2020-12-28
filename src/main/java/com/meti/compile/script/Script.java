package com.meti.compile.script;

import java.util.List;
import java.util.stream.Stream;

public interface Script {
	Stream<String> streamParent();

	List<String> listParent();

	String name();
}