package com.meti.compile.feature.function;

import com.meti.api.core.EF1;
import com.meti.compile.token.Node;
import com.meti.compile.token.Type;
import com.meti.compile.feature.field.Field;
import com.meti.compile.feature.primitive.Primitive;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public abstract class Function implements Node {
	protected final Field identity;
	protected final List<Field> parameters;

	Function(Field identity, List<Field> parameters) {
		this.identity = identity;
		this.parameters = parameters;
	}

	@Override
	public boolean is(Group group) {
		return group == Group.Function;
	}

	@Override
	public Optional<Field> findIdentity() {
		return Optional.of(identity);
	}

	@Override
	public <E extends Exception> Node mapByFields(EF1<Field, Field, E> mapper) throws E {
		var newParameters = new ArrayList<Field>();
		for (Field parameter : parameters) {
			newParameters.add(mapper.apply(parameter));
		}
		return copy(mapper.apply(identity), newParameters);
	}

	protected abstract Node copy(Field identity, List<Field> parameters);

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
