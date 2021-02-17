package com.meti;

import java.util.Optional;

import static com.meti.ImplicitType.ImplicitType_;

public class FieldLexer implements Lexer<Field> {
	public static final Lexer<Field> FieldLexer_ = new FieldLexer();

	private FieldLexer() {
	}

	@Override
	public Optional<Field> lex(Input input) {
		var typeSeparator = input.firstChar(':');
		var valueSeparator = input.firstChar('=');
		var hasType = typeSeparator != -1;
		var hasValue = valueSeparator != -1;
		if (hasType || hasValue) {
			var field = lexValid(input, typeSeparator, valueSeparator, hasType, hasValue);
			return Optional.of(field);
		}
		return Optional.empty();
	}

	private Field lexValid(Input input, int typeSeparator, int valueSeparator, boolean hasType, boolean hasValue) {
		Field field;
		if (hasType && hasValue) {
			var type = new Content(input.slice(typeSeparator + 1, valueSeparator));
			var name = input.slice(0, typeSeparator);
			var value = new Content(input.slice(valueSeparator + 1));
			field = new DefaultField(type, name, value);
		} else if (hasType) {
			//TODO: empty fields
			throw new UnsupportedOperationException();
		} else {
			var name = input.slice(0, valueSeparator);
			var value = new Content(input.slice(valueSeparator + 1));
			field = new DefaultField(ImplicitType_, name, value);
		}
		return field;
	}
}
