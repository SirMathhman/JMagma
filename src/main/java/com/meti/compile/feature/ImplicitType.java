package com.meti.compile.feature;

import com.meti.compile.token.Type;

public class ImplicitType implements Type {
	public static final Type ImplicitType_ = new ImplicitType();

	private ImplicitType() {
	}

	@Override
	public String render(String value) {
		return "? " + value;
	}
}
