package com.meti.compile.token;

import com.meti.api.magma.collect.stream.Stream;
import com.meti.api.magma.collect.stream.Streams;
import com.meti.compile.content.Equatable;

public class Input implements Equatable<Input> {
	private final String content;

	public Input(String content) {
		this.content = content;
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

	public Input slice(int beginIndex, int endIndex) {
		return new Input(content.substring(beginIndex, endIndex));
	}

	public Stream<Character> stream() {
		return Streams.ofIntRange(0, getContent().length())
				.map(getContent()::charAt);
	}

	public Input trim() {
		return new Input(getContent().trim());
	}
}
