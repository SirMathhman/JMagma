package com.meti;

import java.io.IOException;

public record FileSource(File sourceFile) implements Source {
	static FileSource FileSource(File sourceFile) {
		return new FileSource(sourceFile);
	}

	@Override
	public String read() throws IOException {
		return sourceFile.readAsString();
	}
}
