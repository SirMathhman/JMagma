package com.meti.api.io.file.nio;

import com.meti.api.io.file.Path;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static com.meti.api.io.file.nio.NIOFileSystem.NIO_FILE_SYSTEM__;
import static org.junit.jupiter.api.Assertions.*;

class NIOPathTest {

	@Test
	void compareToEqual() throws IOException {
		var first = NIO_FILE_SYSTEM__.findWorking()
				.resolve("test");
		var second = NIO_FILE_SYSTEM__.findWorking()
				.resolve("test");
		assertEquals(0, first.compareTo(second));
	}
}