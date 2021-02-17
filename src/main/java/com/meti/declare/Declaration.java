package com.meti.declare;

import com.meti.F1E1;
import com.meti.Field;
import com.meti.Token;
import com.meti.attribute.Attribute;
import com.meti.attribute.AttributeException;
import com.meti.attribute.FieldAttribute;

import java.util.Objects;
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
	public <E extends Exception> Token mapByField(Attribute.Name name, F1E1<Field, Field, E> attribute) throws AttributeException, E {
		return name == Attribute.Name.Identity
				? new Declaration(attribute.apply(identity))
				: this;
	}

	@Override
	public Stream<Attribute.Name> stream(Attribute.Type type) {
		return type == Attribute.Type.Field
				? Stream.of(Attribute.Name.Identity)
				: Stream.empty();
	}

	@Override
	public int hashCode() {
		return Objects.hash(identity);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Declaration that = (Declaration) o;
		return Objects.equals(identity, that.identity);
	}

	@Override
	public String toString() {
		return "{\"identity\":%s}".formatted(identity);
	}
}
