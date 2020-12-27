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

public record NIOPath(Path value) implements com.meti.api.io.Path {
	@Override
	public com.meti.api.io.Path asAbsolute() {
		return new NIOPath(value.toAbsolutePath());
	}

	@Override
	public String toString() {
		return value.toString();
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
		return NIOFile(Files.createFile(value));
	}

	@Override
	public boolean doesNotExist() {
		return !exists();
	}

	@Override
	public boolean exists() {
		return Files.exists(value);
	}

	private File asFile() {
		return NIOFile(value);
	}

	@Override
	public boolean hasExtensionOf(String extension) {
		return value.getFileName().toString().endsWith("." + extension);
	}

	@Override
	public String name() {
		var internalName = value.getFileName().toString();
		return internalName.contains(".") ? internalName.substring(0, internalName.indexOf('.')) : internalName;
	}

	@Override
	public Option<Directory> existingAsDirectory() {
		if (Files.isDirectory(value)) {
			return Some(new NIODirectory(value));
		} else {
			return None();
		}
	}

	@Override
	public Directory ensureDirectory() throws IOException {
		if (!Files.exists(value)) Files.createDirectory(value);
		return new NIODirectory(value);
	}

	@Override
	public com.meti.api.io.Path resolve(String name) {
		return new NIOPath(value.resolve(name));
	}

	@Override
	public Option<File> existingAsFile() {
		if (Files.isRegularFile(value)) {
			return Some(NIOFile(value));
		} else {
			return None();
		}
	}

	@Override
	public List<String> names() {
		var names = new ArrayList<String>();
		var count = value.getNameCount();
		for (int i = 0; i < count; i++) {
			names.add(value.getName(i).toString());
		}
		return names;
	}

	@Override
	public Directory ensureDirectories() throws IOException {
		if (Files.exists(value)) {
			if (Files.isDirectory(value)) {
				return new NIODirectory(value);
			} else {
				throw new IOException("'" + value + "' isn't a directory.");
			}
		}
		Files.createDirectories(value);
		return new NIODirectory(value);
	}
}
