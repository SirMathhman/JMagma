package com.meti.compile;

import com.meti.compile.feature.scope.Lexer;
import com.meti.compile.token.EmptyField;
import com.meti.compile.token.Field;
import com.meti.compile.token.Token;
import com.meti.compile.token.ValueField;

import static com.meti.compile.MagmaLexingStage.MagmaLexingStage_;

public class FieldLexer implements Lexer<Field> {
	static final Lexer<Field> FieldLexer = new FieldLexer();

	private FieldLexer() {
	}

	@Override
	public boolean canLex(String line) {
		return true;
	}

	@Override
	public Field lex(String line) {
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
		Token type = MagmaLexingStage_.lexType(typeString);
		if (valueSeparator == -1) {
			return new EmptyField(type, name);
		} else {
			var valueSlice = line.substring(valueSeparator + 1);
			var valueString = valueSlice.trim();
			var token = MagmaLexingStage_.lexNode(valueString);
			return new ValueField(type, name, token);
		}
	}
}