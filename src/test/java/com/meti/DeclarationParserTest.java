package com.meti;

import org.junit.jupiter.api.Test;

import static com.meti.DeclarationParser.DeclarationParser_;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DeclarationParserTest {

	@Test
	void parse() throws AttributeException, ParseException, ResolutionException {
		var stack = new MapStack();
		var identity = new DefaultField(IntegerType.unsigned(8), "x", Integer.Zero);
		var current = new Declaration(identity);
		var oldState = new State(stack, current);
		var newState = DeclarationParser_.parse(oldState).orElseThrow();
		assertTrue(newState.getStack().isDefined("x"));
	}
}