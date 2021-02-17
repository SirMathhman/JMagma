package com.meti;

import com.meti.attribute.Attribute;
import com.meti.attribute.AttributeException;
import com.meti.declare.Declaration;
import com.meti.integer.Integer;
import com.meti.integer.IntegerType;
import com.meti.parse.MapStack;
import com.meti.parse.ParseException;
import com.meti.parse.State;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.meti.declare.DeclarationParser.DeclarationParser_;
import static com.meti.ImplicitType.ImplicitType_;
import static org.junit.jupiter.api.Assertions.*;

class DeclarationParserTest {
	@Test
	void already_defined() throws ParseException {
		var identity = new DefaultField(IntegerType.unsigned(8), new Input("x"), Integer.Zero);
		var stack = new MapStack().define(new ListScript(List.of("Main")), identity);
		var current = new Declaration(identity);
		var oldState = new State(stack, current);
		assertThrows(ParseException.class, () -> DeclarationParser_.parse(oldState));
	}

	@Test
	void implicit() throws ParseException, AttributeException {
		var stack = new MapStack();
		var identity = new DefaultField(ImplicitType_, new Input("x"), Integer.Zero);
		var current = new Declaration(identity);
		var oldState = new State(stack, current);
		var newState = DeclarationParser_.parse(oldState).orElseThrow();
		var actual = newState.getCurrent().apply(Attribute.Name.Identity).computeField();
		var expected = new DefaultField(IntegerType.signed(16), new Input("x"), Integer.Zero);
		assertEquals(expected, actual);
	}

	@Test
	void parse() throws AttributeException, ParseException, ResolutionException {
		var stack = new MapStack();
		var identity = new DefaultField(IntegerType.unsigned(8), new Input("x"), Integer.Zero);
		var current = new Declaration(identity);
		var oldState = new State(stack, current);
		var newState = DeclarationParser_.parse(oldState).orElseThrow();
		assertTrue(newState.getStack().isDefined("x"));
	}
}