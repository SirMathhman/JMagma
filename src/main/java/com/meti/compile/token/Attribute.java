package com.meti.compile.token;

import com.meti.api.collect.stream.Stream;
import com.meti.api.core.Equatable;
import com.meti.api.core.Stringable;

public interface Attribute extends Equatable<Attribute>, Stringable {
	default Input computeInput() throws AttributeException {
		throw new AttributeException("Not input.");
	}

	default Stream<Token.Flag> computeStreamOfFlags() throws AttributeException {
		throw new AttributeException("Not a stream of flags.");
	}

	default Stream<Token> computeStreamOfTokens() throws AttributeException {
		throw new AttributeException("Not a stream of tokens.");
	}

	default Token computeToken() throws AttributeException {
		throw new AttributeException("Not a token.");
	}

	enum Name {
		Name,
		Type,
	}

	enum Type {
		Node,
		NodeList,
		Type,
		TypeList,
		Field,
		FieldList
	}
}
