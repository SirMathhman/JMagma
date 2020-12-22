package com.meti.api.io;

import java.nio.file.Paths;

public class NIOFileSystem implements FileSystem {
	public static final FileSystem NIOFileSystem_ = new NIOFileSystem();

	public NIOFileSystem() {
	}

	@Override
	public Path Root() {
		return new NIOPath(Paths.get("."));
	}
}