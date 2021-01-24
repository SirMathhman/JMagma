package com.meti;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static com.meti.Application.Application_;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ApplicationTest {
	private static Path targetDirectory;
	private static Path target;
	private static Path build;
	private static Path sourceDirectory;
	private static Path sourceFile;

	@AfterEach
	void tearDown() throws IOException {
		Files.deleteIfExists(target);
		Files.deleteIfExists(targetDirectory);
		Files.deleteIfExists(sourceFile);
		Files.deleteIfExists(sourceDirectory);
		Files.deleteIfExists(build);
	}

	@Test
	void target_exists() throws IOException {
		setUp("test0", "Main.mg", "test1", "Main.c", "test0\ntest1\n", "def main() : I16 => {return 0;}");
		Application_.run();
		assertTrue(Files.exists(target));
	}

	void setUp(String sourceDirectory, String sourceName, String targetDirectory, String targetName, String buildContent, String sourceContent) throws IOException {
		var root = Paths.get(".");
		build = root.resolve("build");

		ApplicationTest.sourceDirectory = root.resolve(sourceDirectory);
		sourceFile = ApplicationTest.sourceDirectory.resolve(sourceName);

		ApplicationTest.targetDirectory = root.resolve(targetDirectory);
		target = ApplicationTest.targetDirectory.resolve(targetName);

		if (!Files.exists(build)) Files.createFile(build);
		if (!Files.exists(ApplicationTest.sourceDirectory)) Files.createDirectories(ApplicationTest.sourceDirectory);
		if (!Files.exists(sourceFile)) Files.createFile(sourceFile);

		Files.writeString(build, buildContent);
		Files.writeString(sourceFile, sourceContent);
	}

	@Test
	void target_content() throws IOException {
		setUp("test0", "Main.mg", "test1", "Main.c", "test0\ntest1\n", "def main() : I16 => {return 0;}");
		Application_.run();
		assertEquals("int main(){return 0;}", Files.readString(target));
	}
}
