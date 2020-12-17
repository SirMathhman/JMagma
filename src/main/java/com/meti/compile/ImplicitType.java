package com.meti.compile;

public class ImplicitType implements Type {
	public static final Type ImplicitType_ = new ImplicitType();

	private ImplicitType() {
	}

	@Override
	public String render(String value) {
		return "? " + value;
	}
}
