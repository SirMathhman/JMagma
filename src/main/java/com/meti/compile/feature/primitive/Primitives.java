package com.meti.compile.feature.primitive;

import com.meti.compile.token.Attribute;
import com.meti.compile.token.GroupAttribute;
import com.meti.compile.token.StringAttribute;
import com.meti.compile.token.Token;

public enum Primitives implements Token {
	U8, U16, U32, U64,
	I8, I16, I32, I64,
	Void, Any,
	Bool;

	@Override
	public Attribute apply(Query query) {
		return switch (query) {
			case Group -> GroupAttribute.Primitive;
			case Value -> new StringAttribute(name());
			default -> throw new UnsupportedOperationException();
		};
	}
}