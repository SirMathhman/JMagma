package com.meti.compile.io;

import com.meti.api.magma.io.Directory;
import com.meti.api.magma.io.File;
import com.meti.api.magma.io.IOException_;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static com.meti.api.java.io.NIOFileSystem.NIOFileSystem_;
import static org.junit.jupiter.api.Assertions.assertEquals;

class DirectoryLoaderTest {
	private static final String Expected = "test";
	private Directory directory;
	private File file;

	@Test
	void read() throws IOException_ {
		var loader = new DirectoryLoader(directory);
		var actual = loader.load(new ListSource(Collections.singletonList("Main.mg")));
		assertEquals(Expected, actual);
	}

	@BeforeEach
	void setUp() throws IOException_ {
		directory = NIOFileSystem_.Root().ensureAsDirectory();
		file = directory.resolve("Main.mg")
				.ensureAsFile()
				.writeAsString(Expected);

	}

	@Test
	void tearDown() throws IOException_ {
		file.delete();
	}
}