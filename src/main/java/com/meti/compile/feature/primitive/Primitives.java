package com.meti.compile.feature.primitive;

import com.meti.compile.token.Attribute;
import com.meti.compile.token.GroupAttribute;
import com.meti.compile.token.StringAttribute;
import com.meti.compile.token.Token;

public enum Primitives implements Token {
	U8("unsigned char"),
	U16("unsigned int"),
	U32("unsigned long"),
	U64("unsigned long long"),
	I8("char"),
	I16("int"),
	I32("long"),
	I64("long long"),
	Void("void"), Any("void"),
	Bool("int");

	private final String value;

	Primitives(String value) {
		this.value = value;
	}

	@Override
	public Attribute apply(Query query) {
		return switch (query) {
			case Group -> GroupAttribute.Primitive;
			case Value -> new StringAttribute(value);
			default -> throw new UnsupportedOperationException();
		};
	}
}