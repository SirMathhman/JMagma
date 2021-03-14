package com.meti;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ApplicationTest {
	@Test
	void test() throws IOException {
		var path = Paths.get(".", "Main.c");
		Files.writeString(path, "int main(){return 0;}");
		assertEquals("int main(){return 0;}", Files.readString(path));
		Files.deleteIfExists(path);
	}
}
