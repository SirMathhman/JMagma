package com.meti.api.java.io;

import com.meti.api.magma.io.IOException_;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static com.meti.api.java.io.NIOFileSystem.NIOFileSystem_;
import static org.junit.jupiter.api.Assertions.assertTrue;

class NIOPathTest {

	@Test
	void ensureAsFile() throws IOException_, IOException {
		NIOFileSystem_.Root()
				.ensureAsDirectory()
				.resolve("Main.mg")
				.ensureAsFile();
		var nativePath = Paths.get(".", "Main.mg");
		assertTrue(Files.exists(nativePath));
		Files.delete(nativePath);
	}
}