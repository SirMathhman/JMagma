package com.meti.compile.token;

import java.util.Collections;
import java.util.List;

public record Parent(List<Token> lines) implements Token {
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
				var attribute = line.apply(Query.Value);
				var string = attribute.asString();
				builder.append(string);
			} else {
				var format = "Cannot render a node of type '%s'.";
				var message = format.formatted(line.apply(Query.Group));
				throw new UnsupportedOperationException(message);
			}
		}
		return builder.toString();
	}

	@Override
	public Token copy(Query query, Attribute attribute) {
		return query == Query.Lines ? new Parent(attribute.asTokenList()) : this;
	}

	@Override
	public List<Query> list(Attribute.Type type) {
		return type == Attribute.Type.NodeList ?
				Collections.singletonList(Query.Lines) :
				Collections.emptyList();
	}
}
