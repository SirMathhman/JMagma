package com.meti.api.io.file.nio;

import com.meti.api.collect.stream.Stream;
import com.meti.api.io.file.Directory;
import com.meti.api.io.file.File;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static com.meti.api.collect.stream.ArrayStream.ArrayStream;

public class NIODirectory implements Directory {
	private final Path path;

	private NIODirectory(Path path) {
		this.path = path;
	}

	public static NIODirectory NIODirectory(Path path) {
		return new NIODirectory(path);
	}

	@Override
	public com.meti.api.io.file.Path resolve(String child) {
		return NIOPath.NIOPath(path.resolve(child));
	}

	@Override
	public Stream<File> listFiles() throws IOException {
		var array = Files.list(path)
				.filter(Files::isRegularFile)
				.toArray(Path[]::new);
		return ArrayStream(array).map(NIOFile::NIOFile);
	}

	@Override
	public Stream<Directory> listDirectories() throws IOException {
		var array = Files.list(path)
				.filter(Files::isDirectory)
				.toArray(Path[]::new);
		return ArrayStream(array).map(NIODirectory::NIODirectory);
	}
}
