package com.meti;

import org.junit.jupiter.api.Test;

import java.util.List;

import static com.meti.MagmaLexingStage.MagmaLexingStage_;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

class MagmaLexingStageTest {

	@Test
	void lex() throws LexException {
		var first = new Declaration(new DefaultField(IntegerType.signed(16), new Input("x"), new Integer(420)));
		var second = new Declaration(new DefaultField(IntegerType.unsigned(32), new Input("y"), new Integer(100)));
		var expected = List.of(first, second);
		var actual = MagmaLexingStage_.lex(new Input("x : I16 = 420; y : U32 = 100"));
		assertIterableEquals(expected, actual);
	}
}