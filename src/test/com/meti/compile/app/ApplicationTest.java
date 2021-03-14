package com.meti.compile.app;

import com.meti.api.io.File;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Paths;

import static com.meti.api.io.NIOPathFile.NIOPathFile;
import static com.meti.compile.app.EqualityCompiler.WithTarget.EqualityCompiler;
import static com.meti.compile.app.InlineApplication.InlineApplication;
import static com.meti.compile.source.FileSource.FileSource;
import static com.meti.compile.target.FileTarget.FileTarget;
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
		var source = "def main() => {return 0;}";
		var target = "int main(){return 0;}";
		assertContentEquals(resolve("mg"), resolve("c"),
				source, target,
				EqualityCompiler(source).complete(target));
	}

	private static File resolve(final String extension) {
		return resolve("Main", extension);
	}

	private static void assertContentEquals(File sourceFile, File targetFile, String sourceString, String expectedTarget, Compiler compiler) throws IOException {
		sourceFile.write(sourceString);
		InlineApplication(FileSource(sourceFile))
				.withTarget(FileTarget(targetFile))
				.complete(compiler)
				.execute();
		assertEquals(expectedTarget, targetFile.readAsString());

		targetFile.deleteIfExists();
		sourceFile.deleteIfExists();
	}

	@Test
	void validate_integer() throws IOException {
		assertContentSame(resolve("mg"), resolve("c"), "0");
	}

	private static void assertContentSame(File file, File file1, String content) throws IOException {
		assertContentEquals(file, file1, content, content, EqualityCompiler(content).complete(content));
	}

	@Test
	void validate_name() throws IOException {
		var name = "test";
		assertContentSame(resolve(name, "mg"), resolve(name, "c"), "");
	}
}
