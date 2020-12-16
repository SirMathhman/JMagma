package com.meti.exec.compile.render.field;

import com.meti.api.collect.list.List;
import com.meti.api.collect.string.Strings;
import com.meti.api.core.Option;
import com.meti.api.extern.Function1;
import com.meti.exec.compile.render.Node;
import com.meti.exec.compile.render.Type;

import static com.meti.api.collect.string.JSONNode.ArrayNode;
import static com.meti.api.core.Some.Some;

public class FieldWithValue extends AbstractField {
	private final Node value;

	public FieldWithValue(List<Flag> flags, String name, Type type, Node value) {
		super(flags, name, type);
		this.value = value;
	}

	@Override
	public Option<String> render() {
		Function1<String, Option<String>> mapper = typeString -> value.render().map(valueString -> typeString + "=" + valueString);
		return type.render(name).flatMap(mapper);
	}

	@Override
	public boolean equalsTo(Field other) {
		return other.isNamed(name) &&
		       other.isTyped(type) &&
		       other.hasFlags(flags) &&
		       other.findDefaultValue()
				       .map(value::equalsTo)
				       .orElse(false);
	}

	@Override
	public Option<Node> findDefaultValue() {
		return Some(value);
	}

	@Override
	public String asString() {
		flags.stream()
				.map(Flag::name)
				.map(Strings::toLowerCase)
				.map(value -> "\"" + value + "\"")
				.foldLeft(ArrayNode, (array, value) -> array.addString(value));
		return """
				{
					"name" : "%o",
					"value
				}""";
	}
}
