package com.meti.exec.compile;

import java.lang.reflect.Field;

public interface Stack {
	boolean isDefined(String name);

	Stack define(Field field);
}
