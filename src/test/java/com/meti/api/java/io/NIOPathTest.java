package com.meti.api.java.io;

import com.meti.api.java.collect.JavaLists;
import com.meti.api.magma.collect.CollectionException;
import com.meti.api.magma.io.IOException_;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static com.meti.api.java.io.NIOFileSystem.NIOFileSystem_;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class NIOPathTest {
	@Test
	void apply() {
	}

	@Test
	void ensureAsDirectory() throws IOException_, IOException {
		NIOFileSystem_.root()
				.ensureAsDirectory()
				.resolve("Main")
				.ensureAsDirectory();
		var nativePath = Paths.get(".", "Main");
		assertTrue(Files.exists(nativePath));
		assertTrue(Files.isDirectory(nativePath));
		Files.delete(nativePath);
	}

	@Test
	void ensureAsFile() throws IOException_, IOException {
		NIOFileSystem_.root()
				.ensureAsDirectory()
				.resolve("Main.mg")
				.ensureAsFile();
		var nativePath = Paths.get(".", "Main.mg");
		assertTrue(Files.exists(nativePath));
		Files.delete(nativePath);
	}

	@Test
	void existingAsFile() {
	}

	@Test
	void exists() {
	}

	@Test
	void listNames() throws CollectionException {
		var expected = List.of("first", "second", "third");
		var absolute = JavaLists.toJava(NIOFileSystem_.absolute("first", "second", "third")
				.listNames());
		assertIterableEquals(expected, absolute);
	}

	@Test
	void testEquals() {
	}

	@Test
	void testHashCode() {
	}

	@Test
	void testToString() {
	}

	@Test
	void value() {
	}
}