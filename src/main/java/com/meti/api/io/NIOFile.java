package com.meti.api.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public record NIOFile(Path value) implements File {
	static NIOFile NIOFile(Path value) {
		return new NIOFile(value);
	}

	@Override
	public String readString() throws IOException {
		return Files.readString(value);
	}

	@Override
	public com.meti.api.io.Path asPath() {
		return new NIOPath(value);
	}

	@Override
	public NIOFile writeString(String output) throws IOException {
		Files.writeString(value, output);
		return NIOFile(value);
	}
}
