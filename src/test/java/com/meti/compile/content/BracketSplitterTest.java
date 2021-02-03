package com.meti.compile.content;

import com.meti.api.magma.collect.stream.StreamException;
import com.meti.compile.token.Input;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static com.meti.compile.content.BracketSplitter.BracketSplitter_;
import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

class BracketSplitterTest {
	@Test
	void stream() throws StreamException {
		var expected = singletonList("{;}");
		var input = new Input("{;}");
		var stream = BracketSplitter_.stream(input).map(Input::getContent);
		var actual = stream.fold(emptyList(), this::append);
		assertIterableEquals(expected, actual);
	}

	private List<String> append(List<String> strings, String s) {
		var copy = new ArrayList<>(strings);
		copy.add(s);
		return copy;
	}
}