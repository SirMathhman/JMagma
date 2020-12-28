package com.meti.compile;

import com.meti.compile.script.ListScript;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ListScriptTest {
	@Test
	void testEquals() {
		var script = new ListScript(List.of("com", "com/meti"), "Test");
		assertEquals(script, script);
	}
}