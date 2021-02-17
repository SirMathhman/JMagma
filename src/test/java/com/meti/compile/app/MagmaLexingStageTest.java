package com.meti.compile.app;

import com.meti.compile.feature.declare.Declaration;
import com.meti.compile.feature.integer.Integer;
import com.meti.compile.feature.integer.IntegerType;
import com.meti.compile.lex.LexException;
import com.meti.compile.token.DefaultField;
import com.meti.compile.token.Field;
import com.meti.compile.token.RootInput;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.meti.compile.app.MagmaLexingStage.MagmaLexingStage_;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

class MagmaLexingStageTest {

	@Test
	void lex() throws LexException {
		var first = new Declaration(new DefaultField(List.of(Field.Flag.CONST), new RootInput("x"), IntegerType.signed(16), new Integer(420)));
		var second = new Declaration(new DefaultField(List.of(Field.Flag.CONST), new RootInput("y"), IntegerType.unsigned(32), new Integer(100)));
		var expected = List.of(first, second);
		var actual = MagmaLexingStage_.lex(new RootInput("const x : I16 = 420; const y : U32 = 100"));
		assertIterableEquals(expected, actual);
	}
}