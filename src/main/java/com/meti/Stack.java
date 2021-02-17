package com.meti;

import java.util.Optional;

public interface Stack {
	Optional<Field> apply(String name);

	Stack define(Field field);

	Stack enter();

	Stack exit();

	boolean isDefined(String name);

	Stack pop(Script script);

	Stack push(Script script);
}
