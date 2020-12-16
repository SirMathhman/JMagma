package com.meti.compile;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CompilerTest {

	@Test
	void compileMain() {
		assertEquals("#include <stdio.h>\nint main(){printf(\"Hello World!\");return 0;}", """
				import native stdio;
				native def printf(format : String, value : Any...) : Void;
				printf("Hello World!);
				""");
	}
}