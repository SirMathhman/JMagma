package com.meti.compile.token;

import com.meti.compile.token.attribute.Attribute;
import com.meti.compile.token.attribute.AttributeException;
import com.meti.compile.token.attribute.TokenAttribute;
import com.meti.core.F1E1;

import java.util.stream.Stream;

public interface Token {
	default <E extends Exception> Token mapByField(Attribute.Name name, F1E1<Field, Field, E> attribute) throws AttributeException, E {
		throw new AttributeException("Invalid attribute to map as a field.");
	}

	default <E extends Exception> Token mapByToken(Attribute.Name name, F1E1<Token, Token, E> attribute) throws AttributeException, E {
		return copy(name, new TokenAttribute(attribute.apply(apply(name).computeToken())));
	}

	default Attribute apply(Attribute.Name name) throws AttributeException {
		throw new AttributeException("No attributes are present.");
	}

	default Token copy(Attribute.Name name, Attribute attribute) throws AttributeException {
		throw new AttributeException("Invalid attribute to copy.");
	}

	default Stream<Attribute.Name> stream(Attribute.Type type) {
		return Stream.empty();
	}

	enum Type implements Attribute {
		Special, Declaration, Implicit, Content, Assignment, Integer
	}
}
