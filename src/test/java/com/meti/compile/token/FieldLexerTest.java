package com.meti.compile.token;

import com.meti.compile.lex.LexException;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.meti.compile.token.FieldLexer.FieldLexer_;
import static org.junit.jupiter.api.Assertions.assertEquals;

class FieldLexerTest {

	@Test
	void lex() throws LexException {
		var type = new Content(new RootInput("I16"));
		var value = new Content(new RootInput("420"));
		var expected = new DefaultField(List.of(Field.Flag.CONST), new RootInput("x"), type, value);
		var input = new RootInput("const x : I16 = 420");
		var optional = FieldLexer_.lex(input);
		var actual = optional.orElseThrow();
		assertEquals(expected, actual);
	}
}