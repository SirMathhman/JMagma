package com.meti;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public record NIOPathFile(Path source) implements File {
	static NIOPathFile NIOPathFile(Path source) {
		return new NIOPathFile(source);
	}

	@Override
	public void deleteIfExists() throws IOException {
		Files.deleteIfExists(source);
	}

	@Override
	public String readAsString() throws IOException {
		return Files.readString(source);
	}

	@Override
	public void write(String sourceString) throws IOException {
		Files.writeString(source, sourceString);
	}
}
