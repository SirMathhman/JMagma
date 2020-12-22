package com.api.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class NIOFile implements File {
	private final Path value;

	public NIOFile(Path value) {
		this.value = value;
	}

	@Override
	public String readString() throws IOException {
		return Files.readString(value);
	}

	@Override
	public void delete() throws IOException {
		Files.delete(value);
		new NIOPath(value);
	}

	@Override
	public com.api.io.Path asPath() {
		return new NIOPath(value);
	}

	@Override
	public com.api.io.Path writeString(String output) throws IOException {
		Files.writeString(value, output);
		return new NIOPath(value);
	}
}
