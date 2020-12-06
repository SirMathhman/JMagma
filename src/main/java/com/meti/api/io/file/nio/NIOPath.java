package com.meti.api.io.file.nio;

import com.meti.api.core.Option;
import com.meti.api.io.file.Directory;
import com.meti.api.io.file.Extant;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static com.meti.api.core.Some.Some;

public class NIOPath implements com.meti.api.io.file.Path {
	private final Path path;

	private NIOPath(Path path) {
		this.path = path;
	}

	public static com.meti.api.io.file.Path NIOPath(Path path) {
		return new NIOPath(path);
	}

	@Override
	public Extant ensuringAsFile() throws IOException {
		if (!Files.exists(path)) Files.createFile(path);
		return NIOExtant.NIOExtant(path);
	}

	@Override
	public Option<Extant> existingAsFile() {
		return Some(path)
				.filter(Files::exists)
				.filter(Files::isRegularFile)
				.map(NIOExtant::NIOExtant);
	}

	@Override
	public boolean isExtant() {
		return Files.exists(path);
	}

	@Override
	public Option<Directory> existingAsDirectory() {
		return Some(path)
				.filter(Files::exists)
				.filter(Files::isDirectory)
				.map(NIODirectory::NIODirectory);
	}
}
