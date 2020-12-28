package com.meti.compile;

import com.meti.api.io.File;

import java.io.IOException;
import java.util.Collections;

import static com.meti.compile.MagmaCompiler.MagmaCompiler_;
import static org.junit.jupiter.api.Assertions.*;

public class CompiledTest {
	protected void assertSource(String input, String target) {
		assertSource(input, target, "");
	}

	protected void assertSource(String input, String target, String header) {
		try {
			Target<CClass, File> targetImpl = (script, value) -> {
				assertEquals(target, value.render(CClass.Source));
				assertEquals(header, value.render(CClass.Header));
				return Collections.emptyList();
			};
			var source = new StringSource(input);
			var files = MagmaCompiler_.compile(source, targetImpl);
			assertTrue(files.isEmpty());
		} catch (CompileException | IOException e) {
			fail(e);
		}
	}
}
