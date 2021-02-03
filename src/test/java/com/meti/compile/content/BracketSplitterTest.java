package com.meti.compile.content;

import com.meti.compile.token.Input;
import org.junit.jupiter.api.Test;

import static com.meti.MagmaAssertions.assertEquals;
import static com.meti.compile.content.BracketSplitter.BracketSplitter_;
import static com.meti.compile.content.ListStates.EmptyListState;
import static com.meti.compile.content.ListStates.ListState;

class BracketSplitterTest {
	@Test
	void process() {
		var expected = ListState().of(new Input("x")).complete();
		var actual = BracketSplitter_.process(EmptyListState, 'x').complete();
		assertEquals(expected, actual);
	}
}