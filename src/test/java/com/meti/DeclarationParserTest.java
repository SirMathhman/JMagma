package com.meti;

import org.junit.jupiter.api.Test;

import java.util.List;

import static com.meti.DeclarationParser.DeclarationParser_;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DeclarationParserTest {

	@Test
	void already_defined() throws ParseException {
		var identity = new DefaultField(IntegerType.unsigned(8), "x", Integer.Zero);
		var stack = new MapStack().define(new ListScript(List.of("Main")), identity);
		var current = new Declaration(identity);
		var oldState = new State(stack, current);
		assertThrows(ParseException.class, () -> DeclarationParser_.parse(oldState));
	}

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