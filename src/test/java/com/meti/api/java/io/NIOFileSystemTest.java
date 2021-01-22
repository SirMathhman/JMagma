package com.meti.api.java.io;

import com.meti.api.magma.collect.IndexException;
import org.junit.jupiter.api.Test;

import java.nio.file.Paths;

import static com.meti.api.java.io.NIOFileSystem.NIOFileSystem_;
import static org.junit.jupiter.api.Assertions.*;

class NIOFileSystemTest {

	@Test
	void root() {
		var expected = new NIOPath(Paths.get("."));
		var actual = NIOFileSystem_.root();
		assertEquals(expected, actual);
	}

	@Test
	void absolute() throws IndexException {
		var expected = new NIOPath(Paths.get("."));
		var actual = NIOFileSystem_.absolute(".");
		assertEquals(expected, actual);
	}
}