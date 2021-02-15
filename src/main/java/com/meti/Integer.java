package com.meti;

class Integer implements Token {
	private final Input input;

	public Integer(Input input) {
		this.input = input;
	}

	@Override
	public Attribute apply(Attribute.Name name) throws AttributeException {
		return switch (name) {
			case Group -> Group.Integer;
			case Content -> new InputAttribute(input);
			default -> throw new AttributeException();
		};
	}
}
