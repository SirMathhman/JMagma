package com.meti.compile.scope.initialize;

import com.meti.compile.Node;
import com.meti.compile.content.ContentNode;
import com.meti.compile.content.ContentType;
import com.meti.compile.scope.field.Field;
import org.junit.jupiter.api.Test;

import static com.meti.compile.scope.field.Field.Field;
import static org.junit.jupiter.api.Assertions.assertEquals;

class InitializationTokenizerTest {
	@Test
	void tokenizeFunctionalType(){
		Field identity = Field()
				.withName("main")
				.withType(new ContentType("() => I16"))
				.withFlag(Field.Flag.CONST)
				.complete();
		Node expected = new Initialization(identity, new ContentNode("test"));
		Node actual = new InitializationTokenizer("const main : () => I16 = test")
				.tokenize()
				.orElseThrow();
		assertEquals(expected, actual);
	}

	@Test
	void tokenizeFunctionAsValue() {
		Field identity = Field()
				.withName("main")
				.withType(new ContentType("() => I16"))
				.withFlag(Field.Flag.CONST)
				.complete();
		Node expected = new Initialization(identity, new ContentNode("def main_() : I16 => {return 0;}"));
		Node actual = new InitializationTokenizer("const main : () => I16 = def main_() : I16 => {return 0;}")
				.tokenize()
				.orElseThrow();
		assertEquals(expected, actual);
	}

	@Test
	void tokenize() {
		Field identity = Field()
				.withName("x")
				.withType(new ContentType("I16"))
				.withFlag(Field.Flag.CONST)
				.complete();
		Node expected = new Initialization(identity, new ContentNode("0"));
		Node actual = new InitializationTokenizer("const x : I16 = 0")
				.tokenize()
				.orElseThrow();
		assertEquals(expected, actual);
	}
}