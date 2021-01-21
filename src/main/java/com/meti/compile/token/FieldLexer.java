package com.meti.compile.token;

import com.meti.compile.stage.Lexer;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class FieldLexer implements Lexer<Field> {
	public static final Lexer<Field> FieldLexer_ = new FieldLexer();

	public FieldLexer() {
	}

	@Override
	public Optional<Field> lex(String content) {
		if (content.contains(":") && content.contains("=")) {
			var typeSeparator = content.indexOf(':');
			var valueSeparator = content.indexOf('=');
			var keySlice = content.substring(0, typeSeparator);
			var keyString = keySlice.trim();
			var space = keyString.lastIndexOf(' ');
			var flagSlice = keyString.substring(0, space);
			var flagString = flagSlice.trim();
			var flags = Arrays.stream(flagString.split(" "))
					.filter(s -> !s.isBlank())
					.map(String::trim)
					.map(String::toUpperCase)
					.map(Field.Flag::valueOf)
					.collect(Collectors.toList());
			var nameSlice = keySlice.substring(space + 1);
			var name = nameSlice.trim();

			var typeSlice = content.substring(typeSeparator + 1, valueSeparator);
			var typeString = typeSlice.trim();
			var type = new Content(typeString);

			var valueSlice = content.substring(valueSeparator + 1);
			var valueString = valueSlice.trim();
			var value = new Content(valueString);

			return Optional.of(new ValuedField(flags, name, type, value));
		}
		return Optional.empty();
	}

	private static record ValuedField(List<Flag> flags,
	                                  String name,
	                                  Token type,
	                                  Token value) implements Field {
		@Override
		public List<Flag> findFlags() {
			return flags;
		}

		@Override
		public String findName() {
			return name;
		}

		@Override
		public Token findType() {
			return type;
		}

		@Override
		public Optional<Token> findValue() {
			return Optional.of(value);
		}

		@Override
		public Field withType(Token type) {
			return new ValuedField(flags, name, type, value);
		}

		@Override
		public Optional<Field> withValue(Token value) {
			return Optional.of(new ValuedField(flags, name, type, value));
		}
	}
}
