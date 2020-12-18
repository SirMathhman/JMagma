package com.meti.compile.feature.function;

import com.meti.compile.feature.Node;
import com.meti.compile.feature.field.Field;

import java.util.List;

public class Implementation extends Function {
	private final Node value;

	private Implementation(Field identity, List<Field> parameters, Node value) {
		super(identity, parameters);
		this.value = value;
	}

	public static Function Implementation(Field identity, List<Field> parameters, Node value) {
		return new Implementation(identity, parameters, value);
	}

	@Override
	protected String complete(String header) {
		return header + value.render();
	}
}
