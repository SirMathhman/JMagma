package com.meti.compile.parse;

import com.meti.compile.token.Field;
import com.meti.compile.io.Script;

import java.util.Optional;

public interface Stack {
	Optional<Field> apply(String name);

	Optional<Script> current();

	Stack define(Script script, Field field);

	Stack enter(Script script);

	Stack exit(Script script);

	boolean isDefined(String name);

	Stack center(Script script);
}
