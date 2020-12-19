package com.meti.compile.feature.extern;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DirectiveTest {

	@Test
	void toNode() {
		assertEquals("#include <stdio.h>\n", Directive.Include.toNode("<stdio.h>").render());
	}
}