package com.meti.compile.feature.variable;

import com.meti.compile.token.Input;
import com.meti.compile.token.Token;
import com.meti.compile.token.attribute.Attribute;
import com.meti.compile.token.attribute.AttributeException;
import com.meti.compile.token.attribute.InputAttribute;

public class Variable implements Token {
	private final Input value;

	public Variable(Input value) {
		this.value = value;
	}

	@Override
	public Attribute apply(Attribute.Name name) throws AttributeException {
		return switch (name) {
			case Type -> Type.Variable;
			case Value -> new InputAttribute(value);
			default -> throw new AttributeException();
		};
	}
}
