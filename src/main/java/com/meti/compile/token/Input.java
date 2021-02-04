package com.meti.compile.token;

import com.meti.api.magma.collect.IndexException;
import com.meti.api.magma.collect.stream.Stream;
import com.meti.api.magma.collect.stream.Streams;
import com.meti.api.magma.collect.string.Stringable;
import com.meti.compile.content.Equatable;

public record Input(String content) implements Equatable<Input>, Stringable {
	@Override
	public String asString() {
		return "\"%s\"".formatted(content);
	}

	@Override
	public boolean equalsTo(Input state) {
		return content.equals(state.content);
	}

	public boolean hasContent() {
		return !getContent().isBlank();
	}

	public String getContent() {
		return content;
	}

	public boolean hasSameContent(Input input) {
		return getContent().equals(input.getContent());
	}

	public Input slice(int lower, int upper) throws IndexException {
		var length = content.length();
		if (lower > length) {
			var format = "Lower bound of %d exceeds internal length of %d";
			var message = format.formatted(lower, length);
			throw new IndexException(message);
		}
		if (upper > length) {
			var format = "Upper bound of %d exceeds internal length of %d";
			var message = format.formatted(upper, length);
			throw new IndexException(message);
		}
		return new Input(content.substring(lower, upper));
	}

	public Stream<Character> stream() {
		return Streams.ofIntRange(0, getContent().length())
				.map(getContent()::charAt);
	}

	public Input trim() {
		return new Input(getContent().trim());
	}
}
