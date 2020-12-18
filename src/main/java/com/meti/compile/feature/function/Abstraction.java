package com.meti.compile.feature.function;

import com.meti.compile.feature.field.Field;

import java.util.List;

public class Abstraction extends Function {
	private Abstraction(Field identity, List<Field> parameters) {
		super(identity, parameters);
	}

	static Abstraction Abstraction(Field identity, List<Field> parameters) {
		return new Abstraction(identity, parameters);
	}

	@Override
	protected String complete(String header) {
		return header + ";";
	}
}
