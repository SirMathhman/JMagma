package com.meti.compile.feature.primitive;

import com.meti.compile.token.Type;

public enum Primitive implements Type {
	U8("unsigned char"),
	U16("unsigned int"),
	U32("unsigned long"),
	U64("unsigned long long"),
	I8("char"),
	I16("int"),
	I32("long"),
	I64("long long"),
	Void("void");

	private final String content;

	Primitive(String content) {
		this.content = content;
	}

	@Override
	public String render(String value) {
		return content + " " + value;
	}
}
