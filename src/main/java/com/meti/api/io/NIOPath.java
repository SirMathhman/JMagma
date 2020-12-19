package com.meti.api.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class NIOPath {
	public static final NIOPath Root = new NIOPath(Paths.get("."));
	private final Path path;

	public NIOPath(Path path) {
		this.path = path;
	}

	public NIOFile ensureAsFile() throws IOException {
		if (doesNotExist()) {
			return createAsFile();
		}
		return asFile();
	}

	public NIOFile createAsFile() throws IOException {
		return new NIOFile(Files.createFile(getPath()));
	}

	public boolean doesNotExist() {
		return !doesExist();
	}

	public NIOPath resolve(String name) {
		return new NIOPath(getPath().resolve(name));
	}

	public boolean doesExist() {
		return Files.exists(getPath());
	}

	public Path getPath() {
		return path;
	}

	public NIOFile asFile() {
		return new NIOFile(path);
	}
}
