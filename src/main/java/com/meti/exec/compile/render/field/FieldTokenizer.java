package com.meti.exec.compile.render.field;

import com.meti.api.collect.list.ArrayList;
import com.meti.api.collect.stream.Stream;
import com.meti.api.collect.stream.StreamException;
import com.meti.api.collect.string.Strings;
import com.meti.api.core.Option;
import com.meti.api.extern.ExceptionFunction0;
import com.meti.api.extern.Function0;
import com.meti.api.extern.Function1;
import com.meti.exec.compile.render.*;

import static com.meti.api.collect.string.SimpleStringBuffer.StringBuffer;
import static com.meti.api.collect.string.Strings.*;
import static com.meti.exec.compile.render.ContentNode.ContentNode;
import static com.meti.exec.compile.render.TokenizationException.TokenizationException;
import static com.meti.exec.compile.render.field.FieldBuilders.FieldBuilder;

public class FieldTokenizer extends AbstractTokenizer<Field> {
	private FieldTokenizer(String content) {
		super(content);
	}

	public static FieldTokenizer FieldTokenizer(String content) {
		return new FieldTokenizer(content);
	}

	@Override
	public Option<Field> tokenize() throws TokenizationException {
		Function1<Integer, Field> withTypeSeparator = typeSeparator -> {
			var headerSlice = slice(content, 0, typeSeparator);
			var header = trim(headerSlice);
			var withName = tokenizeHeader(header);
			Function1<Integer, Field> withBoth = equals -> {
				var typeSlice = slice(content, typeSeparator + 1, equals);
				var typeTrim = trim(typeSlice);
				var type = ContentType.ContentType(typeTrim);
				var value = tokenizeValue(equals);
				return withName.withType(type)
						.withValue(value)
						.complete();
			};
			Function0<Field> withType = () -> {
				var typeSlice = slice(content, typeSeparator + 1, content.length());
				var typeTrim = trim(typeSlice);
				var type = ContentType.ContentType(typeTrim);
				return withName.withType(type).complete();
			};
			return first(content, c -> c == '=')
					.map(withBoth)
					.orElseGet(withType);
		};
		ExceptionFunction0<Field, TokenizationException> withoutTypeSeparator = () -> {
			Function1<Integer, Field> withDefaultValue = valueSeparator -> {
				var headerSlice = slice(content, 0, valueSeparator);
				var header = trim(headerSlice);
				var value = tokenizeValue(valueSeparator);
				return tokenizeHeader(header)
						.withType(ImplicitType.ImplicitType_)
						.withValue(value).complete();
			};
			Function0<TokenizationException> asInvalid = () -> TokenizationException("No type or default value is present.");
			return first(content, c -> c == '=')
					.map(withDefaultValue)
					.orElseThrow(asInvalid);
		};
		return first(content, c -> c == ':')
				.map(withTypeSeparator)
				.ensure(withoutTypeSeparator);
	}

	private ContentNode tokenizeValue(int separator) {
		var valueSlice = slice(content, separator + 1, content.length());
		var valueString = trim(valueSlice);
		return ContentNode(valueString);
	}

	private FieldBuilders.WithName tokenizeHeader(String header) {
		Function1<Integer, FieldBuilders.WithName> usingSpace = space -> {
			var flagsSlice = slice(header, 0, space);
			var flagsString = trim(flagsSlice);
			var nameSlice = slice(flagsString, space + 1, flagsString.length());
			var name = trim(nameSlice);
			try {
				return tokenizeFlags(flagsString)
						.foldLeft(FieldBuilder(), FieldBuilders.Empty::withFlag)
						.withName(name);
			} catch (StreamException e) {
				return FieldBuilder().withName(name);
			}
		};
		Function0<FieldBuilders.WithName> asNoSpace = () -> FieldBuilder().withName(header);
		return last(header, c -> c == ' ')
				.map(usingSpace)
				.orElseGet(asNoSpace);
	}

	private Stream<Field.Flag> tokenizeFlags(String flagString) throws StreamException {
		var flagStrings = ArrayList.empty(Strings::equalsTo);
		var buffer = StringBuffer();
		for (int i = 0; i < flagString.length(); i++) {
			char c = flagString.charAt(i);
			if (c == ' ') {
				flagStrings = flagStrings.add(buffer.asString());
				buffer = StringBuffer();
			} else {
				buffer = buffer.add(c);
			}
		}
		return flagStrings.add(buffer.asString())
				.stream()
				.filter(Strings::isContent)
				.mapExceptionally(Field.Flag::valueOf);
	}
}
