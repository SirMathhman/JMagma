package com.meti.compile.stage;

import com.meti.api.java.collect.JavaLists;
import com.meti.api.magma.collect.Sequences;
import com.meti.api.magma.collect.StreamException;
import com.meti.compile.CompileException;
import com.meti.compile.feature.block.Blocks;
import com.meti.compile.feature.function.Implementation;
import com.meti.compile.feature.function.Return;
import com.meti.compile.feature.function.FunctionType;
import com.meti.compile.feature.primitive.Integer;
import com.meti.compile.feature.primitive.Primitives;
import com.meti.compile.token.EmptyField;
import com.meti.compile.token.Field;
import com.meti.compile.token.Token;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static com.meti.compile.stage.MagmaLexerStage.MagmaLexerStage_;
import static java.util.Collections.emptyList;
import static org.junit.jupiter.api.Assertions.assertEquals;

class MagmaLexerStageTest {

	@Test
	void apply() throws CompileException {
		var expected = createImplementation();
		var actual = MagmaLexerStage_.apply("def main() : I16 => {return 0;}");
		assertEquals(expected, actual);
	}

	private Token createImplementation() {
		var value = new Integer("0");
		var returns = new Return(value);
		var body = Blocks.of(JavaLists.fromJava(Collections.singletonList(returns)));
		Token identityType;
		try {
			identityType = Sequences.stream(JavaLists.<Token>fromJava(emptyList()))
					.fold(FunctionType.Empty, FunctionType.WithoutReturn::withParameter)
					.withReturn(Primitives.I16).complete();
		} catch (StreamException e) {
			identityType = FunctionType.Empty.withReturn((Token) Primitives.I16)
					.complete();
		}
		var identity = new EmptyField(Collections.singletonList(Field.Flag.DEF), "main", identityType);
		return new Implementation(identity, emptyList(), body);
	}
}