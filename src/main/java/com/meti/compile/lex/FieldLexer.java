package com.meti.compile.lex;

import com.meti.api.magma.core.None;
import com.meti.api.magma.core.Option;
import com.meti.compile.token.*;

import static com.meti.api.magma.core.Some.Some;
import static com.meti.compile.lex.MagmaLexingStage.MagmaLexingStage_;

public class FieldLexer implements Lexer<Field> {
	static final Lexer<Field> FieldLexer_ = new FieldLexer();

	private FieldLexer() {
	}

	@Override
	public Option<Field> lex(Input input) {
		var line = input.getContent();
		if (line.contains(":") || line.contains("=")) {
			var typeSeparator = line.indexOf(':');
			var valueSeparator = line.indexOf('=');
			var keysSlice = line.substring(0, typeSeparator);
			var keysString = keysSlice.trim();
			var space = keysString.lastIndexOf(' ');
			var nameSlice = keysString.substring(space + 1);
			var name = nameSlice.trim();
			var extent = valueSeparator == -1 ? line.length() : valueSeparator;
			var typeSlice = line.substring(typeSeparator + 1, extent);
			var typeString = typeSlice.trim();
			Token type = MagmaLexingStage_.lexType(new Input(typeString));
			Field result;
			if (valueSeparator == -1) {
				result = new EmptyField(type, name);
			} else {
				var valueSlice = line.substring(valueSeparator + 1);
				var valueString = valueSlice.trim();
				var token = MagmaLexingStage_.lexNode(new Input(valueString));
				result = new ValueField(type, name, token);
			}
			return Some(result);
		} else {
			return new None<>();
		}
	}
}