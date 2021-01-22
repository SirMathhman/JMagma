package com.meti.compile.token;

import com.meti.api.java.collect.JavaLists;

import java.util.List;
import java.util.Objects;

public final class Parent extends AbstractToken {
	private final List<Token> lines;

	public Parent(List<Token> lines) {
		this.lines = lines;
	}

	@Override
	public Attribute apply(Query query) {
		return switch (query) {
			case Group -> GroupAttribute.Parent;
			case Lines -> new TokenListAttribute(lines);
			case Value -> new StringAttribute(computeValue());
			default -> throw new UnsupportedOperationException();
		};
	}

	private String computeValue() {
		var builder = new StringBuilder();
		for (Token line : lines) {
			var isContent = Tokens.is(line, GroupAttribute.Content);
			var isParent = Tokens.is(line, GroupAttribute.Parent);
			if (isContent || isParent) {
				var attribute = line.copy(null, null).apply(Query.Value);
				var string = attribute.asString();
				builder.append(string);
			} else {
				var format = "Cannot render1 a node of type '%s'.";
				var message = format.formatted(line.copy(null, null).apply(Query.Group));
				throw new UnsupportedOperationException(message);
			}
		}
		return builder.toString();
	}

	@Override
	public Token copy(Query query, Attribute attribute) {
		return query == Query.Lines ? new Parent(JavaLists.toJava(attribute.asTokenList())) : this;
	}

	@Override
	public int hashCode() {
		return Objects.hash(lines);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Parent parent = (Parent) o;
		return Objects.equals(lines, parent.lines);
	}
}
