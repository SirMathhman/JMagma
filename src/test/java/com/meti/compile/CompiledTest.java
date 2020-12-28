package com.meti.compile;

import java.io.IOException;
import java.util.Collections;

import static com.meti.compile.MagmaCompiler.MagmaCompiler_;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class CompiledTest {
	protected void assertSource(String input, String target) {
		assertSource(input, target, "");
	}

	protected void assertSource(String input, String target, String header) {
		try {
			MagmaCompiler_.compile(new StringSource(input), (script, value) -> {
				assertEquals(target, value.render(CClass.Source));
				assertEquals(header, value.render(CClass.Header));
				return Collections.emptyList();
			});
		} catch (CompileException | IOException e) {
			fail(e);
		}
	}
}
