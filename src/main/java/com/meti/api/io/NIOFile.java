package com.meti.api.io;

import java.nio.file.Path;

public class NIOFile {
	private final Path value;

	public NIOFile(Path value) {
		this.value = value;
	}

	public Path getValue() {
		return value;
	}
}
