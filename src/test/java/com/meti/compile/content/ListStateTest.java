package com.meti.compile.content;

import com.meti.api.magma.collect.list.ArrayLists;
import com.meti.api.magma.collect.list.List;
import com.meti.api.magma.collect.stream.StreamException;
import com.meti.compile.token.Input;
import org.junit.jupiter.api.Test;

import static com.meti.MagmaAssertions.assertEquals;
import static com.meti.MagmaAssertions.assertSequenceEquals;
import static com.meti.compile.content.ListStates.ListState;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ListStateTest {

	@Test
	void advance() {
	}

	@Test
	void complete() {
		var input = new Input("test");
		var actual = ListState()
				.of(input)
				.from(1)
				.to(3)
				.complete()
				.complete();
		var expected = ListState()
				.of(input)
				.append(new Input("es"))
				.from(3)
				.to(3)
				.complete();
		assertEquals(actual, expected);
	}

	@Test
	void equalsTo() {
		var input = new Input("testing");
		var expected = ListState()
				.of(input)
				.from(1)
				.to(6)
				.complete();
		var actual = ListState()
				.of(input)
				.from(1)
				.to(6)
				.complete();
		assertEquals(expected, actual);
	}

	@Test
	void isAt() {
		assertTrue(ListState()
				.at(6)
				.complete()
				.isAt(6));
	}

	@Test
	void isLevel() {
		assertTrue(ListState()
				.complete()
				.isLevel());
	}

	@Test
	void isShallow() {
		assertTrue(ListState()
				.at(1)
				.complete()
				.isShallow());
	}

	@Test
	void isStoring() {
		assertTrue(ListState()
				.of(new Input("testing"))
				.from(1)
				.to(6)
				.complete()
				.isStoring(new Input("estin")));
	}

	@Test
	void sink() {
		var expected = ListState().complete();
		var actual = ListState()
				.at(-1)
				.complete()
				.sink();
		assertEquals(expected, actual);
	}

	@Test
	void stream() throws StreamException {
		var first = new Input("first");
		var second = new Input("second");
		var third = new Input("third");
		var expected = ArrayLists.of(first, second, third);
		var actual = ListState().append(first)
				.append(second)
				.append(third)
				.complete()
				.stream()
				.fold(ArrayLists.<Input>empty(), List::add);
		assertSequenceEquals(expected, actual);
	}

	@Test
	void surface() {
		var expected = ListState().complete();
		var actual = ListState()
				.at(1)
				.complete()
				.surface();
		assertEquals(expected, actual);
	}
}