package com.meti.compile.feature.primitive;

import com.meti.compile.token.Attribute;
import com.meti.compile.token.GroupAttribute;
import com.meti.compile.token.StringAttribute;
import com.meti.compile.token.Token;

public record Integer(String value) implements Token {
	@Override
	public Attribute apply(Query query) {
		return switch (query) {
			case Group -> GroupAttribute.Integer;
			case Value -> new StringAttribute(value);
			default -> throw new UnsupportedOperationException();
		};
	}
}
