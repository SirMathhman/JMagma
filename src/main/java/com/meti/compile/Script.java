package com.meti.compile;

import java.util.List;
import java.util.stream.Stream;

public interface Script {
	@Deprecated
	Stream<String> streamParents();

	List<String> parent();

	String name();

}