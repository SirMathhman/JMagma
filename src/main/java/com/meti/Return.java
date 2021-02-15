package com.meti;

record Return(Token value) implements Token {
	@Override
	public Attribute apply(Attribute.Name name) throws AttributeException {
		return switch (name) {
			case Group -> Group.Return;
			case Value -> new TokenAttribute(value);
			default -> throw new AttributeException();
		};
	}
}
