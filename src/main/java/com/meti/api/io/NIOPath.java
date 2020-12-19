package com.meti.api.io;

import com.meti.api.core.Option;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static com.meti.api.core.None.None;
import static com.meti.api.core.Some.Some;

public class NIOPath implements com.meti.api.io.Path {

	private final Path path;

	public NIOPath(Path path) {
		this.path = path;
	}

	@Override
	public com.meti.api.io.Path asAbsolute() {
		return new NIOPath(path.toAbsolutePath());
	}

	@Override
	public String toString() {
		return path.toString();
	}

	@Override
	public Option<File> existing() {
		return Files.exists(path) ? Some(new NIOFile(path)) : None();
	}

	@Override
	public File ensureAsFile() throws IOException {
		if (doesNotExist()) {
			return createAsFile();
		}
		return asFile();
	}

	@Override
	public File createAsFile() throws IOException {
		return new NIOFile(Files.createFile(path));
	}

	@Override
	public boolean doesNotExist() {
		return !exists();
	}

	@Override
	public com.meti.api.io.Path resolve(String name) {
		return new NIOPath(path.resolve(name));
	}

	@Override
	public boolean exists() {
		return Files.exists(path);
	}

	@Override
	public File asFile() {
		return new NIOFile(path);
	}
}
