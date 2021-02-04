package com.meti.compile.content;

import com.meti.api.magma.collect.list.ArrayLists;
import com.meti.api.magma.collect.list.List;
import com.meti.api.magma.collect.stream.StreamException;
import com.meti.compile.token.Input;
import org.junit.jupiter.api.Test;

import static com.meti.MagmaAssertions.assertSequenceEquals;

class ListSplitterTest {

	@Test
	void stream() throws StreamException {
		var item = new Input("{;}");
		var inputs = new Impl().stream(item);
		var expected = ArrayLists.of(item);
		var actual = inputs.fold(ArrayLists.<Input>empty(), List::add);
		assertSequenceEquals(expected, actual);
	}

	protected static class Impl extends ListSplitter {
		@Override
		protected State process(State state, char c) {
			return state.advance();
		}
	}
}