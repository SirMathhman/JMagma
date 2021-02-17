package com.meti.compile.token;

import com.meti.compile.lex.LexException;
import com.meti.compile.lex.Lexer;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import static com.meti.compile.feature.primitive.ImplicitType.ImplicitType_;

public class FieldLexer implements Lexer<Field> {
	public static final Lexer<Field> FieldLexer_ = new FieldLexer();

	private FieldLexer() {
	}

	@Override
	public Optional<Field> lex(Input input) throws LexException {
		Function<Integer, Field> withType = typeSeparator -> {
			Function<Integer, Field> withValue = valueSeparator -> lexBoth(input, typeSeparator, valueSeparator);
			Supplier<Field> withoutValue = () -> {
				throw new UnsupportedOperationException();
			};
			return input.firstChar('=')
					.map(withValue)
					.orElseGet(withoutValue);
		};
		Supplier<Optional<? extends Field>> withoutType = () -> input.firstChar('=')
				.map(valueSeparator -> lexValue(input, valueSeparator));
		return input.firstChar(':').map(withType).or(withoutType);
	}

	private Field lexBoth(Input input, int typeSeparator, int valueSeparator) {
		var type = new Content(input.slice(typeSeparator + 1, valueSeparator));
		var keys = input.slice(0, typeSeparator);
		return lexDefault(input, keys, type, valueSeparator);
	}

	private Field lexDefault(Input input, Input keys, Token type, int valueSeparator) {
		return keys.last(' ').map((Function<Integer, Field>) space -> {
			var slice = keys.slice(0, space);
			var flags = lexFlags(slice);
			var name = keys.slice(space + 1);
			var value = new Content(input.slice(valueSeparator + 1));
			return new DefaultField(flags, name, type, value);
		}).orElseGet(() -> {
			var value = new Content(input.slice(valueSeparator + 1));
			return new DefaultField(new ArrayList<>(), keys, type, value);
		});
	}

	private List<Field.Flag> lexFlags(Input slice) {
		var lines = new ArrayList<Input>();
		var builder = new StringBuilder();
		for (int i = 0; i < slice.size(); i++) {
			var c = slice.apply(i);
			if (c == ' ') {
				lines.add(new RootInput(builder.toString()));
				builder = new StringBuilder();
			} else {
				builder.append(c);
			}
		}
		lines.add(new RootInput(builder.toString()));
		lines.removeIf(Input::isEmpty);
		var flags = lines.stream()
				.map(line -> Field.Flag.valueOf(line.map(String::toUpperCase)))
				.collect(Collectors.toList());
		return flags;
	}

	private Field lexValue(Input input, int valueSeparator) {
		var keys = input.slice(0, valueSeparator);
		return lexDefault(input, keys, ImplicitType_, valueSeparator);
	}
}
