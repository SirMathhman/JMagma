package com.meti.compile.content;

import com.meti.api.magma.collect.stream.StreamException;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static com.meti.compile.content.BracketSplitter.BracketSplitter_;
import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

class BracketSplitterTest {

	@Test
	void process_all() {
		var splitter = new BracketSplitter();
		var list = singletonList("{;;}");
		var expected = new JavaState(list, "", 0);
		var actual = splitter.processAll("{;;}");
		assertEquals(expected, actual);
	}

	@Test
	void process_both() {
		var splitter = new BracketSplitter();
		var state = new JavaState();
		var first = splitter.process(state, '{');
		var actual = splitter.process(first, '}');
		var expected = new JavaState(singletonList("{}"), "", 0);
		assertEquals(expected, actual);
	}

	@Test
	void process_open() {
		var splitter = new BracketSplitter();
		var expected = new JavaState(emptyList(), "{", 1);
		var actual = splitter.processAll("{");
		assertEquals(expected, actual);
	}

	@Test
	void process_open_as_char() {
		var splitter = new BracketSplitter();
		var state = new JavaState();
		var actual = splitter.process(state, '{');
		var expected = new JavaState(emptyList(), "{", 1);
		assertEquals(expected, actual);
	}

	@Test
	void state_equals() {
		var expected = new JavaState(singletonList("test"), "", 0);
		var actual = new JavaState(singletonList("test"), "", 0);
		assertEquals(expected, actual);
	}

	@Test
	void stream() throws StreamException {
		var expected = singletonList("{;}");
		var stream = BracketSplitter_.stream("{;}");
		var actual = stream.fold(emptyList(), this::append);
		assertIterableEquals(expected, actual);
	}

	private List<String> append(List<String> strings, String s) {
		var copy = new ArrayList<>(strings);
		copy.add(s);
		return copy;
	}
}