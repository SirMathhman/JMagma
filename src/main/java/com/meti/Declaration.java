package com.meti;

public class Declaration implements Token {
	private final Field identity;

	public Declaration(Field identity) {
		this.identity = identity;
	}

	@Override
	public Attribute apply(Attribute.Name name) throws AttributeException {
		return switch (name) {
			case Type -> Type.Declaration;
			case Identity -> new FieldAttribute(identity);
			default -> throw new AttributeException();
		};
	}
}
