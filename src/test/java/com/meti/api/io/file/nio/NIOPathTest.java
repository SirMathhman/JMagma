package com.meti.api.io.file.nio;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static com.meti.api.io.file.nio.NIOFileSystem.NIO_FILE_SYSTEM__;
import static org.junit.jupiter.api.Assertions.assertTrue;

class NIOPathTest {

	@Test
	void equalsTo() throws IOException {
		var first = NIO_FILE_SYSTEM__.findWorking()
				.resolve("test");
		var second = NIO_FILE_SYSTEM__.findWorking()
				.resolve("test");
		assertTrue(first.equalsTo(second));
	}
}