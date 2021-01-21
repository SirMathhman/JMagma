package com.meti.compile;

import com.meti.api.magma.io.File;
import com.meti.api.magma.io.IOException_;
import com.meti.api.magma.io.Path;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.meti.api.java.io.NIOFileSystem.NIOFileSystem_;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ApplicationTest {
	private static final Path Root = NIOFileSystem_.Root();
	private File Source;
	private Path Intermediate;

	@Test
	void run() {
		var app = new Application(Root);
		assertDoesNotThrow(app::run);
		assertTrue(Intermediate.exists());
	}

	@BeforeEach
	void setUp() throws IOException_ {
		var rootAsDirectory = Root.ensureAsDirectory();
		Source = rootAsDirectory.resolve("Main.mg").ensureAsFile();
		Source.writeAsString1("def main() : I16 => {return 0;}");
		Intermediate = rootAsDirectory.resolve("Main.c");
	}

	@AfterEach
	void tearDown() throws IOException_ {
		Source.delete();
		var optional = Intermediate.existingAsFile();
		if (optional.isPresent()) optional.get().delete();
	}
}