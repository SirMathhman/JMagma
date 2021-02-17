package com.meti;

import com.meti.compile.feature.declare.Declaration;
import com.meti.compile.feature.integer.Integer;
import com.meti.compile.feature.integer.IntegerType;
import com.meti.compile.lex.LexException;
import com.meti.compile.token.DefaultField;
import com.meti.compile.token.Input;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.meti.compile.app.MagmaLexingStage.MagmaLexingStage_;
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