package com.meti.api.java.io;

import com.meti.api.magma.io.IOException_;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static com.meti.api.java.io.NIOFileSystem.NIOFileSystem_;
import static org.junit.jupiter.api.Assertions.assertEquals;

class NIOFileTest {

	@Test
	void writeAsString() throws IOException_, IOException {
		NIOFileSystem_.root()
				.ensureAsDirectory()
				.resolve("Main.mg")
				.ensureAsFile()
				.writeAsString("test");
		var nativePath = Paths.get(".", "Main.mg");
		assertEquals("test", Files.readString(nativePath));
		Files.delete(nativePath);
	}
}