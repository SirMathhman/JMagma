package com.meti.compile.stage;

import com.meti.api.java.collect.JavaList;
import com.meti.compile.CompileException;
import com.meti.compile.feature.block.Blocks;
import com.meti.compile.feature.function.FunctionType;
import com.meti.compile.feature.function.Implementation;
import com.meti.compile.feature.function.Return;
import com.meti.compile.feature.primitive.Integer;
import com.meti.compile.feature.primitive.Primitives;
import com.meti.compile.token.EmptyField;
import com.meti.compile.token.Field;
import com.meti.compile.token.Token;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static com.meti.compile.stage.MagmaLexerStage.MagmaLexerStage_;
import static java.util.Collections.emptyList;
import static org.junit.jupiter.api.Assertions.*;

class MagmaLexerStageTest {

	@Test
	void apply() throws CompileException {
		var value = new Integer("0");
		var returns = new Return(value);
		Token body = Blocks.of(new JavaList<>(Collections.singletonList(returns)));
		var identityType = new FunctionType(Primitives.I16, emptyList());
		var identity = new EmptyField(Collections.singletonList(Field.Flag.DEF), "main", identityType);
		var expected = new Implementation(identity, emptyList(), body);
		var actual = MagmaLexerStage_.apply("def main() : I16 => {return 0;}");
		assertEquals(expected, actual);
	}
}