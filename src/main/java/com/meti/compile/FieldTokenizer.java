package com.meti.compile;

import com.meti.compile.field.Field;
import com.meti.compile.field.FieldBuilders;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Stream;

import static com.meti.compile.ContentNode.ContentNode;
import static com.meti.compile.ContentType.ContentType;
import static com.meti.compile.field.FieldBuilders.FieldBuilder;
import static com.meti.compile.field.FieldBuilders.None;

public class FieldTokenizer implements Tokenizer<Field> {
	static final Tokenizer<Field> FieldTokenizer_ = new FieldTokenizer();

	private FieldTokenizer() {
	}

	@Override
	public Optional<Field> tokenize(String content) {
		return Optional.of(content)
				.filter(this::isValid)
				.map(this::tokenizeValid);
	}

	private boolean isValid(String content) {
		return content.contains(":") || content.contains("=");
	}

	private Field tokenizeValid(String content) {
		if (content.contains(":") && content.contains("=")) {
			return tokenizeWithBoth(content);
		} else if (content.contains(":")) {
			return tokenizeWithType(content);
		} else {
			return tokenizeWithValue(content);
		}
	}

	private Field tokenizeWithValue(String content) {
		var equals = content.indexOf('=');
		var value = createValue(content, equals + 1);
		return tokenizeHeader(content, equals)
				.withType(ImplicitType.ImplicitType_)
				.withValue(value)
				.complete();
	}

	private Field tokenizeWithType(String content) {
		var colon = content.indexOf(':');
		var type = createType(content, colon + 1, content.length());
		return tokenizeHeader(content, colon)
				.withType(type)
				.complete();
	}

	private Field tokenizeWithBoth(String content) {
		var colon = content.indexOf(':');
		var equals = content.indexOf('=');

		var type = createType(content, equals, colon + 1);
		var value = createValue(content, equals + 1);
		return tokenizeHeader(content, colon)
				.withType(type)
				.withValue(value)
				.complete();
	}

	private FieldBuilders.WithName tokenizeHeader(String content, int extent) {
		var headerSlice = content.substring(0, extent);
		var header = headerSlice.trim();
		if (header.contains(" ")) {
			var space = header.indexOf(' ');
			var flagString = header.substring(0, space);
			var flags = streamFlags(flagString).reduce(FieldBuilder(), None::withFlag, (none, none2) -> none2);
			var name = header.substring(space + 1);
			return flags.withName(name);
		} else {
			return FieldBuilder().withName(header);
		}
	}

	private Node createValue(String content, int from) {
		var valueSlice = content.substring(from);
		var valueContent = valueSlice.trim();
		return ContentNode(valueContent);
	}

	private Type createType(String content, int to, int from) {
		var typeSlice = content.substring(from, to);
		var typeContent = typeSlice.trim();
		return ContentType(typeContent);
	}

	private Stream<Field.Flag> streamFlags(String flagString) {
		return Arrays.stream(flagString.split(" "))
				.filter(s -> !s.isBlank())
				.map(String::toUpperCase)
				.map(Field.Flag::valueOf);
	}
}
