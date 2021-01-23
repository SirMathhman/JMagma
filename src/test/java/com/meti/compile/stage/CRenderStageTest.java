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
import com.meti.compile.token.*;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static com.meti.compile.feature.function.Implementation.Empty;
import static com.meti.compile.stage.CNodeRenderer.CNodeRenderer_;
import static com.meti.compile.stage.CRenderStage.CRenderStage_;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CRenderStageTest {
	@Test
	void render() throws CompileException {
		var node = createFunction();
		assertEquals("int main(){return 0;}", CRenderStage_.render(node)
				.apply(AbstractToken.Query.Value)
				.asString());
	}

	private Token createFunction() {
		Token functionType;
		try {
			functionType = Sequences.stream(JavaLists.fromJava(Collections.<Token>emptyList()))
					.fold(FunctionType.Empty, FunctionType.WithoutReturn::withParameter)
					.withReturn(Primitives.I16).complete();
		} catch (StreamException e) {
			functionType = FunctionType.Empty.withReturn(Primitives.I16)
					.complete();
		}
		Field identity;
		try {
			identity = Sequences.stream(JavaLists.fromJava(Collections.singletonList(Field.Flag.DEF)))
					.fold(Fields.Empty, Fields.Neither::withFlag)
					.withName("main")
					.withType(functionType)
					.complete();
		} catch (StreamException e1) {
			identity = Fields.Empty.withName("main")
					.withType(functionType)
					.complete();
		}
		final List<Token> lines = Collections.singletonList(new Return(new Integer("0")));
		Token body = Blocks.of(JavaLists.fromJava(lines));
		try {
			return Sequences.stream(JavaLists.<Field>fromJava(Collections.emptyList()))
					.fold(Empty, Implementation.Neither::withParameter)
					.withIdentity(identity)
					.withBody(body)
					.complete();
		} catch (StreamException e) {
			return Empty.withIdentity(identity)
					.withBody(body)
					.complete();
		}
	}

	@Test
	void renderBlock() throws CompileException {
		var value = new Integer("0");
		var line = new Return(value);
		Token node = Blocks.of(JavaLists.fromJava(List.of(line)));
		assertEquals("{return 0;}", CRenderStage_.render(node)
				.apply(AbstractToken.Query.Value)
				.asString());
	}

	@Test
	void renderBlockAsParent() throws CompileException {
		var value = new Integer("0");
		var line = new Return(value);
		Token node = Blocks.of(JavaLists.fromJava(List.of(line)));
		var optional = CNodeRenderer_.render(node)
				.map(Optional::of)
				.orElseGet(Optional::empty);
		var rendered = optional.orElseThrow();
		assertEquals("{return 0;}", CRenderStage_.renderParent(rendered)
				.apply(AbstractToken.Query.Value)
				.asString());
	}

	@Test
	void renderBlockAsRoot() throws CompileException {
		var value = new Integer("0");
		var line = new Return(value);
		Token node = Blocks.of(JavaLists.fromJava(List.of(line)));
		assertEquals("{return 0;}", CRenderStage_.renderRoot(node)
				.apply(AbstractToken.Query.Value)
				.asString());
	}

	@Test
	void renderPair() throws CompileException {
		var pair = new Pair(Primitives.I16, new Content("main()"));
		var value = CRenderStage_.render(pair).apply(AbstractToken.Query.Value).asString();
		assertEquals("int main()", value);
	}

	@Test
	void renderParent() throws CompileException {
		var input = createFunction();
		var optional = CNodeRenderer_.render(input)
				.map(Optional::of)
				.orElseGet(Optional::empty);
		var output = optional.orElseThrow();
		Token actual = CRenderStage_.renderParent(output);
		assertEquals("int main(){return 0;}", actual.apply(AbstractToken.Query.Value).asString());
	}

	@Test
	void renderReturn() throws CompileException {
		var value = new Integer("0");
		var line = new Return(value);
		assertEquals("return 0;", CRenderStage_.render(line)
				.apply(AbstractToken.Query.Value)
				.asString());
	}

	@Test
	void renderRoot() throws CompileException {
		var node = createFunction();
		assertEquals("int main(){return 0;}", CRenderStage_.renderRoot(node)
				.apply(AbstractToken.Query.Value)
				.asString());
	}
}