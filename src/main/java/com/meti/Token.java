package com.meti;

import com.meti.attribute.Attribute;
import com.meti.attribute.AttributeException;

import java.util.stream.Stream;

public interface Token {
	default Attribute apply(Attribute.Name name) throws AttributeException {
		throw new AttributeException("No attributes are present.");
	}

	default Token copy(Attribute.Name name, Attribute attribute) throws AttributeException {
		throw new AttributeException("Invalid attribute to copy.");
	}

	default <E extends Exception> Token mapByField(Attribute.Name name, F1E1<Field, Field, E> attribute) throws AttributeException, E {
		throw new AttributeException("Invalid attribute to copy.");
	}

	default <E extends Exception> Token mapByToken(Attribute.Name name, F1E1<Token, Token, E> attribute) throws AttributeException, E {
		throw new AttributeException("Invalid attribute to copy.");
	}

	default Stream<Attribute.Name> stream(Attribute.Type type) {
		return Stream.empty();
	}

	enum Type implements Attribute {
		Special, Declaration, Implicit, Content, Integer
	}
}
