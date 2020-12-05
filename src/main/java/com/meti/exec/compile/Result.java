package com.meti.exec.compile;

public interface Result<E extends Enum<?>> {
	Result<E> with(E group, String result);

	String apply(E group);

	enum Group {
		Target
	}
}
