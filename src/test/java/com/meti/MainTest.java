package com.meti;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MainTest {
	private static final Path Root = Paths.get(".");
	private static final Path Source = Root.resolve("Main.mg");
	private static final Path Target = Root.resolve("Main.c");

	@BeforeEach
	void setUp() throws IOException {
		Files.writeString(Source, "def main() : I16 => {return 0;}");
	}

	@AfterEach
	void tearDown() throws IOException {
		Files.deleteIfExists(Target);
		Files.deleteIfExists(Source);
	}

	@Test
	void test_main() throws IOException {
		Main.main(new String[0]);
		assertEquals("int main(){return 0;}", Files.readString(Target));
	}
}
