package com.meti.compile.feature.assign;

import com.meti.compile.token.Token;
import com.meti.compile.token.attribute.Attribute;
import com.meti.compile.token.attribute.AttributeException;
import com.meti.compile.token.attribute.TokenAttribute;

import java.util.stream.Stream;

public class Assignment implements Token {
	private final Token destination;
	private final Token source;

	public Assignment(Token destination, Token source) {
		this.destination = destination;
		this.source = source;
	}

	@Override
	public Attribute apply(Attribute.Name name) throws AttributeException {
		return switch (name) {
			case Type -> Type.Assignment;
			case Destination -> new TokenAttribute(destination);
			case Source -> new TokenAttribute(source);
			default -> throw new AttributeException();
		};
	}

	@Override
	public Token copy(Attribute.Name name, Attribute attribute) throws AttributeException {
		return switch (name) {
			case Destination -> new Assignment(attribute.computeToken(), source);
			case Source -> new Assignment(destination, attribute.computeToken());
			default -> throw new AttributeException();
		};
	}

	@Override
	public Stream<Attribute.Name> stream(Attribute.Type type) {
		return type == Attribute.Type.Node
				? Stream.of(Attribute.Name.Destination, Attribute.Name.Source)
				: Stream.empty();
	}
}
