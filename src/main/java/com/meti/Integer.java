package com.meti;

record Integer(Input input) implements Token {
	@Override
	public Attribute apply(Attribute.Name name) throws AttributeException {
		return switch (name) {
			case Group -> Group.Integer;
			case Content -> new InputAttribute(input);
			default -> throw new AttributeException();
		};
	}
}
