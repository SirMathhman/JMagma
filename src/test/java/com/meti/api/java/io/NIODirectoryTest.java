package com.meti.api.java.io;

import com.meti.api.magma.collect.IndexException;
import com.meti.api.magma.io.IOException_;
import org.junit.jupiter.api.Test;

import static com.meti.api.java.io.NIOFileSystem.NIOFileSystem_;
import static org.junit.jupiter.api.Assertions.assertEquals;

class NIODirectoryTest {

	@Test
	void relativize() throws IOException_, IndexException {
		var temp = NIOFileSystem_.root().ensureAsDirectory()
				.resolve("test")
				.ensureAsDirectory();
		var expected = NIOFileSystem_.absolute("Main.mg");
		var actual = temp.relativize(temp.resolve("Main.mg"));
		assertEquals(expected, actual);
		temp.delete();
	}

	@Test
	void resolve() throws IOException_, IndexException {
		var expected = NIOFileSystem_.absolute(".", "test");
		var actual = NIOFileSystem_.root().ensureAsDirectory().resolve("test");
		assertEquals(expected, actual);
	}

	@Test
	void streamTree() {
	}

	@Test
	void value() {
	}
}