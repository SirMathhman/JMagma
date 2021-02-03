package com.meti.compile.content;

import com.meti.api.java.collect.list.JavaList;
import com.meti.api.magma.collect.stream.StreamException;
import com.meti.compile.token.Input;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static com.meti.compile.content.BracketSplitter.BracketSplitter_;
import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.*;

class BracketSplitterTest {

	@Test
	void process_all() {
		var splitter = new BracketSplitter();
		var list = singletonList("{;;}");
		var expected = new ListState(new JavaList<>(list), "", 0);
		var actual = splitter.processAll("{;;}");
		assertTrue(expected.equalsTo(actual));
	}

	@Test
	void process_both() {
		var splitter = new BracketSplitter();
		var state = new ListState();
		var first = splitter.process(state, '{');
		var actual = splitter.process(first, '}');
		var expected = new ListState(new JavaList<>(singletonList("{}")), "", 0);
		assertTrue(expected.equalsTo(actual));
	}

	@Test
	void process_open() {
		var splitter = new BracketSplitter();
		var expected = new ListState(new JavaList<>(emptyList()), "{", 1);
		var actual = splitter.processAll("{");
		assertTrue(expected.equalsTo(actual));
	}

	@Test
	void process_open_as_char() {
		var splitter = new BracketSplitter();
		var state = new ListState();
		var actual = splitter.process(state, '{');
		var expected = new ListState(new JavaList<>(emptyList()), "{", 1);
		assertTrue(expected.equalsTo(actual));
	}

	@Test
	void state_equals() {
		var expected = new ListState(new JavaList<>(singletonList("test")), "", 0);
		var actual = new ListState(new JavaList<>(singletonList("test")), "", 0);
		assertTrue(expected.equalsTo(actual));
	}

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