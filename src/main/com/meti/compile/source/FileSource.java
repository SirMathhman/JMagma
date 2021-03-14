package com.meti.compile.source;

import com.meti.api.io.File;

import java.io.IOException;

public record FileSource(File sourceFile) implements Source {
	public static FileSource FileSource(File sourceFile) {
		return new FileSource(sourceFile);
	}

	@Override
	public String read() throws IOException {
		return sourceFile.readAsString();
	}
}
