package com.meti.compile.feature.function;

import com.meti.compile.feature.Node;
import com.meti.compile.feature.Type;
import com.meti.compile.feature.field.Field;
import com.meti.compile.feature.primitive.Primitive;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Implementation implements Node {
	private final Field identity;
	private final List<Field> parameters;
	private final Node value;

	private Implementation(Field identity, List<Field> parameters, Node value) {
		this.identity = identity;
		this.parameters = parameters;
		this.value = value;
	}

	public static Implementation Implementation(Field identity, List<Field> parameters, Node value) {
		return new Implementation(identity, parameters, value);
	}

	@Override
	public String render() {
		Function<Type, String> renderHeader = type -> {
			var returnType = type.findChild().orElse(Primitive.Void);
			var paramString = renderParameters();
			Function<String, String> renderReturn = name -> returnType.render(name + paramString);
			return identity.applyToName(renderReturn);
		};
		var header = identity.applyToType(renderHeader);
		return header + value.render();
	}

	private String renderParameters() {
		return parameters.stream()
				.map(Field::render)
				.collect(Collectors.joining(",", "(", ")"));
	}
}
