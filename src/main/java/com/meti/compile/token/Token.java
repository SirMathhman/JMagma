package com.meti.compile.token;

import com.meti.api.collect.stream.Stream;
import com.meti.api.collect.stream.Streams;

public interface Token {
	default Attribute apply(Attribute.Name name) throws AttributeException {
		throw new AttributeException("No attributes are present.");
	}

	default Stream<Attribute.Name> apply(Attribute.Type type) {
		return Streams.empty();
	}

	enum Flag {

	}
}
