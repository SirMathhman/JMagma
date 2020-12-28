package com.meti.compile;

import com.meti.api.io.File;
import org.junit.jupiter.api.Assertions;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import static com.meti.compile.MagmaToCCompiler.MagmaCompiler_;
import static org.junit.jupiter.api.Assertions.*;

public class CompiledTest {
	protected void assertSource(String input, String target) {
		assertSource(input, target, "");
	}

	protected void assertSource(String input, String target, String header) {
		try {
			List<File> files = compileImpl(input, (script, value) -> {
				Assertions.assertEquals(target, value.render(CClass.Source));
				Assertions.assertEquals(header, value.render(CClass.Header));
				return Collections.emptyList();
			});
			assertTrue(files.isEmpty());
		} catch (CompileException | IOException e) {
			fail(e);
		}
	}

	protected void assertSourceThrows(java.lang.Class<? extends Throwable> clazz, String input) {
		assertThrows(clazz, () -> compileImpl(input, (script, value) -> Collections.emptyList()));
	}

	private List<File> compileImpl(String input, Target<CClass, File> targetImpl) throws IOException, CompileException {
		var source = new StringSource(input);
		return MagmaCompiler_.compile(source, targetImpl);
	}
}
