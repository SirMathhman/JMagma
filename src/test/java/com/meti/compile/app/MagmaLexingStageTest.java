package com.meti.compile.app;

import com.meti.compile.feature.declare.Declaration;
import com.meti.compile.feature.integer.Integer;
import com.meti.compile.feature.integer.IntegerType;
import com.meti.compile.lex.LexException;
import com.meti.compile.token.DefaultField;
import com.meti.compile.token.Field;
import com.meti.compile.token.RootInput;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static com.meti.compile.app.MagmaLexingStage.MagmaLexingStage_;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

class MagmaLexingStageTest {

	@Test
	void lex() throws LexException {
		var first = new Declaration(new DefaultField(new ArrayList<Field.Flag>(), IntegerType.signed(16), new RootInput("x"), new Integer(420)));
		var second = new Declaration(new DefaultField(new ArrayList<Field.Flag>(), IntegerType.unsigned(32), new RootInput("y"), new Integer(100)));
		var expected = List.of(first, second);
		var actual = MagmaLexingStage_.lex(new RootInput("x : I16 = 420; y : U32 = 100"));
		assertIterableEquals(expected, actual);
	}
}