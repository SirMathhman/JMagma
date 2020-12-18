package com.meti.compile.feature.function;

import com.meti.compile.feature.Node;
import com.meti.compile.feature.Type;
import com.meti.compile.feature.field.Field;
import com.meti.compile.feature.primitive.Primitive;

import java.util.List;
import java.util.stream.Collectors;

public abstract class Function implements Node {
	private final Field identity;
	private final List<Field> parameters;

	Function(Field identity, List<Field> parameters) {
		this.identity = identity;
		this.parameters = parameters;
	}

	@Override
	public String render() {
		java.util.function.Function<Type, String> renderHeader = type -> {
			var returnType = type.findChild().orElse(Primitive.Void);
			var paramString = renderParameters();
			java.util.function.Function<String, String> renderReturn = name -> returnType.render(name + paramString);
			return identity.applyToName(renderReturn);
		};
		var header = identity.applyToType(renderHeader);
		return complete(header);
	}

	protected abstract String complete(String header);

	private String renderParameters() {
		return parameters.stream()
				.map(Field::render)
				.collect(Collectors.joining(",", "(", ")"));
	}
}
