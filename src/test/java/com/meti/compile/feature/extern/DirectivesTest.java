package com.meti.compile.feature.extern;

import com.meti.compile.token.Node;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DirectivesTest {
	@Test
	void testEquals() {
		assertEquals(createNode(), createNode());
	}

	@Test
	void toNode() {
		assertEquals("#include <stdio.h>\n", createNode().render());
	}

	private Node createNode() {
		return Directives.Include.toNode("<stdio.h>");
	}
}