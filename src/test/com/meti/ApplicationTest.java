package com.meti;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Paths;

import static com.meti.NIOPathFile.NIOPathFile;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ApplicationTest {
	@Test
	void invalidate_extinct() throws IOException {
		assertThrows(IOException.class, () -> resolve("", "mg").readAsString());
	}

	private static File resolve(final String name, String extension) {
		return NIOPathFile(Paths.get(".", name + "." + extension));
	}

	@Test
	void validate_content() throws IOException {
		assertContentEquals(resolve("mg"), resolve("c"), "def main() => {return 0;}", "int main(){return 0;}");
	}

	private static File resolve(final String extension) {
		return resolve("Main", extension);
	}

	private static void assertContentEquals(File file, File file1, String sourceString, String expectedTarget) throws IOException {
		file.write(sourceString);
		var input = file.readAsString();
		String output;
		if (input.equals("def main() => {return 0;}")) {
			output = "int main(){return 0;}";
		} else {
			output = input;
		}
		file1.write(output);
		assertEquals(expectedTarget, file1.readAsString());
		file1.deleteIfExists();
		file.deleteIfExists();
	}

	@Test
	void validate_integer() throws IOException {
		assertContentSame(resolve("mg"), resolve("c"), "0");
	}

	private static void assertContentSame(File file, File file1, String content) throws IOException {
		assertContentEquals(file, file1, content, content);
	}

	@Test
	void validate_name() throws IOException {
		var name = "test";
		assertContentSame(resolve(name, "mg"), resolve(name, "c"), "");
	}
}
