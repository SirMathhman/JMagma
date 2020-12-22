package com.meti.api.io;

import com.meti.api.core.Option;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static com.meti.api.core.None.None;
import static com.meti.api.core.Some.Some;
import static com.meti.api.io.NIOFile.NIOFile;

public record NIOPath(Path path) implements com.meti.api.io.Path {
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
		return Files.exists(path) ? Some(NIOFile(path)) : None();
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
		return NIOFile(Files.createFile(path));
	}

	@Override
	public boolean doesNotExist() {
		return !exists();
	}

	@Override
	public boolean exists() {
		return Files.exists(path);
	}

	private File asFile() {
		return NIOFile(path);
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
	public Directory ensureAsDirectory() throws IOException {
		if (!Files.exists(path)) Files.createDirectory(path);
		return new NIODirectory(path);
	}

	@Override
	public com.meti.api.io.Path resolve(String name) {
		return new NIOPath(path.resolve(name));
	}

	@Override
	public Option<File> existingAsFile() {
		if (Files.isRegularFile(path)) {
			return Some(NIOFile(path));
		} else {
			return None();
		}
	}

	@Override
	public List<String> names() {
		var names = new ArrayList<String>();
		var count = path.getNameCount();
		for (int i = 0; i < count; i++) {
			names.add(path.getName(i).toString());
		}
		return names;
	}
}
