package com.meti.compile.stage;

import com.meti.compile.CompileException;
import com.meti.compile.feature.block.Block;
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

import static com.meti.compile.stage.CRenderStage.CRenderStage_;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CRenderStageTest {

	@Test
	void render() throws CompileException {
		var functionType = new FunctionType(Primitives.I16, Collections.emptyList());
		var identity = new EmptyField(Collections.singletonList(Field.Flag.DEF), "main", functionType);
		var body = new Block(Collections.singletonList(new Return(new Integer("0"))));
		var node = new Implementation(identity, Collections.emptyList(), body);
		assertEquals("int main(){return 0;}", CRenderStage_.render(node)
				.apply(Token.Query.Value)
				.asString());
	}
}