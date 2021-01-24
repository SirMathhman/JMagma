package com.meti;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

class Application {
	static final Application Application_ = new Application();

	private Application() {
	}

	void run() throws IOException {
		Files.writeString(Paths.get(".", "Main.c"), "int main(){return 0;}");
	}
}