package com.meti.api.io.file.nio;

import com.meti.api.io.file.Directory;

import java.nio.file.Path;

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
}
