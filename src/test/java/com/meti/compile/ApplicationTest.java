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
	private Path Root;
	private File Source;
	private Path Intermediate;
	private Path Header;

	@Test
	void run() {
		var app = new Application(Root);
		assertDoesNotThrow(app::run);
		assertTrue(Intermediate.exists());
	}

	@BeforeEach
	void setUp() throws IOException_ {
		Root = NIOFileSystem_.Root()
				.ensureAsDirectory()
				.resolve("test")
				.ensureAsDirectory();

		var rootAsDirectory = Root.ensureAsDirectory();
		Source = rootAsDirectory.resolve("Main.mg").ensureAsFile();
		Source.writeAsString("def main() : I16 => {return 0;}");

		Intermediate = rootAsDirectory.resolve("Main.c");
		Header = rootAsDirectory.resolve("Main.h");
	}

	@AfterEach
	void tearDown() throws IOException_ {
		Source.delete();
		Intermediate.existingAsFile().ifPresent(File::delete);
		Header.existingAsFile().ifPresent(File::delete);
	}
}