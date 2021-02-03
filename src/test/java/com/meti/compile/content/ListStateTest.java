package com.meti.compile.content;

import com.meti.api.magma.collect.stream.Stream;
import com.meti.compile.token.Input;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ListStateTest {

	@Test
	void complete() {
		var input = new Input("testing");
		var state = new ListState(input, 1, 6);
		var completed = state.complete();
		var stream = completed.stream();
	}

	@Test
	void advance() {
	}

	@Test
	void isAt() {
	}

	@Test
	void isLevel() {
	}

	@Test
	void isShallow() {
	}

	@Test
	void isStoring() {
	}

	@Test
	void reset() {
	}

	@Test
	void sink() {
	}

	@Test
	void stream() {
	}

	@Test
	void surface() {
	}

	@Test
	void equalsTo() {
	}
}