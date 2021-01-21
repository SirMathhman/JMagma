package com.meti.compile;

import java.util.List;
import java.util.stream.Stream;

public interface Script {
	@Deprecated
	Stream<String> streamParent();

	List<String> listParent();

	String name();
}