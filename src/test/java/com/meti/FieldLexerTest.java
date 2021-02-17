package com.meti;

import com.meti.lex.LexException;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static com.meti.FieldLexer.FieldLexer_;
import static org.junit.jupiter.api.Assertions.assertEquals;

class FieldLexerTest {

	@Test
	void lex() {
		var type = new Content(new Input("I16"));
		var value = new Content(new Input("420"));
		var expected = new DefaultField(type, new Input("x"), value);
		Optional<Field> optional;
		try {
			optional = FieldLexer_.lex(new Input("x : I16 = 420"));
		} catch (LexException e) {
			e.printStackTrace();
			optional = Optional.empty();
		}
		var actual = optional.orElseThrow();
		assertEquals(expected, actual);
	}
}