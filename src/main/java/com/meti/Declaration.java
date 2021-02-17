package com.meti;

import java.util.stream.Stream;

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
			default -> throw new AttributeException("Invalid attribute: " + name);
		};
	}

	@Override
	public Token copy(Attribute.Name name, Attribute attribute) throws AttributeException {
		if (name == Attribute.Name.Identity) return new Declaration(attribute.computeField());
		throw new AttributeException();
	}

	@Override
	public Stream<Attribute.Name> stream(Attribute.Type type) {
		return type == Attribute.Type.Field
				? Stream.of(Attribute.Name.Identity)
				: Stream.empty();
	}
}
