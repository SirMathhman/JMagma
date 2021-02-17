package com.meti.compile.token.attribute;

public class BooleanAttribute implements Attribute {
	public static final Attribute TrueAttribute = new BooleanAttribute(true);
	public static final Attribute FalseAttribute = new BooleanAttribute(false);
	private final boolean state;

	private BooleanAttribute(boolean state) {
		this.state = state;
	}

	@Override
	public boolean computeBoolean() throws AttributeException {
		return state;
	}
}
