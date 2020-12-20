package com.meti.api.io;

import com.meti.api.core.Option;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

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
	public boolean exists() {
		return Files.exists(path);
	}

	@Override
	public File asFile() {
		return new NIOFile(path);
	}

	@Override
	public List<File> list() {
		return null;
	}

	@Override
	public List<File> walk() {
		return null;
	}

	@Override
	public com.meti.api.io.Path resolveSibling(String name, String extension) {
		return new NIOPath(path.resolveSibling(name + "." + extension));
	}

	@Override
	public boolean hasExtensionOf(String extension) {
		return path.getFileName().toString().endsWith("." + extension);
	}

	@Override
	public String name() {
		var internalName = path.getFileName().toString();
		return internalName.contains(".") ? internalName.substring(0, internalName.indexOf('.')) : internalName;
	}

	@Override
	public Option<Directory> existingAsDirectory() {
		if (Files.isDirectory(path)) {
			return Some(new NIODirectory(path));
		} else {
			return None();
		}
	}

	@Override
	public Directory ensuringAsDirectory() throws IOException {
		if (!Files.exists(path)) Files.createDirectory(path);
		return new NIODirectory(path);
	}

	@Override
	public com.meti.api.io.Path resolve(String name) {
		return new NIOPath(path.resolve(name));
	}
}
