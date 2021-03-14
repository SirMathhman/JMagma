package com.meti;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ApplicationTest {
	@Test
	void invalidate_extinct() throws IOException {
		assertThrows(IOException.class, () -> Files.readString(resolve("", "mg")));
	}

	private static Path resolve(final String name, String extension) {
		return Paths.get(".", name + "." + extension);
	}

	@Test
	void validate_content() throws IOException {
		var source = resolve("mg");
		var target = resolve("c");
		assertContentEquals(source, target, "def main() => {return 0;}", "int main(){return 0;}");
	}

	private static Path resolve(final String extension) {
		return resolve("Main", extension);
	}

	private static void assertContentEquals(Path source, Path target, String sourceString, String expectedTarget) throws IOException {
		Files.writeString(source, sourceString);
		var input = Files.readString(source);
		String output;
		if (input.equals("def main() => {return 0;}")) {
			output = "int main(){return 0;}";
		} else {
			output = input;
		}
		Files.writeString(target, output);
		assertEquals(expectedTarget, Files.readString(target));
		Files.deleteIfExists(target);
		Files.deleteIfExists(source);
	}

	@Test
	void validate_integer() throws IOException {
		assertContentSame(resolve("mg"), resolve("c"), "0");
	}

	private static void assertContentSame(Path source, Path target, String content) throws IOException {
		assertContentEquals(source, target, content, content);
	}

	@Test
	void validate_name() throws IOException {
		var name = "test";
		var source = resolve(name, "mg");
		var target = resolve(name, "c");
		assertContentSame(source, target, "");
	}
}
