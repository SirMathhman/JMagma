package com.meti.exec.compile;

import com.meti.api.collect.stream.Stream;
import com.meti.api.core.Comparable;
import com.meti.api.extern.ExceptionFunction1;
import com.meti.api.extern.ExceptionFunction2;

public interface Package extends Comparable<Package> {
	@Deprecated
	<A, B, E extends Exception> B apply(ExceptionFunction1<Stream<String>, A, E> first, ExceptionFunction2<A, String, B, E> second) throws E;

	Stream<String> stream();

	String name();
}
