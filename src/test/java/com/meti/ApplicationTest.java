package com.meti;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static com.meti.Application.Application_;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ApplicationTest {
	private static final Path Target = Paths.get(".", "Main.c");
	private static final Path Build = Paths.get(".", "build");
	private static final Path SourceDirectory = Paths.get(".", "test0");
	private static final Path SourceFile = Paths.get(".", "test0", "Main.mg");

	@BeforeEach
	void setUp() throws IOException {
		if (!Files.exists(Build)) Files.createFile(Build);
		if (!Files.exists(SourceDirectory)) Files.createDirectories(SourceDirectory);
		if (!Files.exists(SourceFile)) Files.createFile(SourceFile);

		Files.writeString(Build, "test0\ntest1");
		Files.writeString(SourceFile, "def main() : I16 => {return 0;}");
	}

	@AfterEach
	void tearDown() throws IOException {
		Files.deleteIfExists(Target);
		Files.deleteIfExists(SourceFile);
		Files.deleteIfExists(SourceDirectory);
		Files.deleteIfExists(Build);
	}

	@Test
	void target_exists() throws IOException {
		Application_.run();
		assertTrue(Files.exists(Target));
	}

	@Test
	void target_content() throws IOException {
		Application_.run();
		assertEquals("int main(){return 0;}", Files.readString(Target));
	}
}
