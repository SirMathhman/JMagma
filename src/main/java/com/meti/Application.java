package com.meti;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

class Application {
	static final Application Application_ = new Application();

	private Application() {
	}

	void run() throws IOException {
		var parent = Paths.get(".", "test1");
		if (!Files.exists(parent)) Files.createDirectories(parent);
		var target = parent.resolve("Main.c");
		Files.writeString(target, "int main(){return 0;}");
	}
}