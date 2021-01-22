package com.meti.compile.io;

import com.meti.api.magma.collect.Lists;
import com.meti.api.magma.io.Directory;
import com.meti.api.magma.io.File;
import com.meti.api.magma.io.IOException_;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.meti.api.java.io.NIOFileSystem.NIOFileSystem_;
import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

class DirectoryLoaderTest {
	private static final String Expected = "test";
	private Directory directory;
	private File file;

	@Test
	void listSources() throws IOException_ {
		var loader = new DirectoryLoader(directory);
		var source = new ListSource(singletonList("Main"));
		var expected = singletonList(source);
		var actual = Lists.toJava(loader.listSources());
		assertIterableEquals(expected, actual);
	}

	@Test
	void read() throws IOException_ {
		var loader = new DirectoryLoader(directory);
		var source = new ListSource(singletonList("Main"));
		var actual = loader.load(source);
		assertEquals(Expected, actual);
	}

	@BeforeEach
	void setUp() throws IOException_ {
		directory = NIOFileSystem_.Root().ensureAsDirectory()
				.resolve("test")
				.ensureAsDirectory();
		file = directory.resolve("Main.mg")
				.ensureAsFile()
				.writeAsString(Expected);
	}

	@AfterEach
	void tearDown() throws IOException_ {
		file.delete();
		directory.delete();
	}
}