package com.meti.exec.compile;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {
	private static final Path Source = Paths.get(".", "Main.mgs");
	private static final Path Target = Paths.get(".", "main.c");

	@BeforeEach
	void setUp() throws IOException {
		cleanup();

		Files.writeString(Source, "");
	}

	private void cleanup() throws IOException {
		Files.deleteIfExists(Source);
		Files.deleteIfExists(Target);
	}

	@AfterEach
	void tearDown() throws IOException {
		cleanup();
	}

	@Test
	void main() throws IOException {
		Main.main(new String[0]);
		assertTrue(Files.exists(Target));
		assertEquals("int main(){return 0;}", Files.readString(Target));
	}
}