package com.meti.api.io.file.nio;

import com.meti.api.io.file.Directory;
import com.meti.api.io.file.FileSystem;

import java.io.IOException;
import java.nio.file.Paths;

import static com.meti.api.io.file.nio.NIOPath.NIOPath;

public class NIOFileSystem implements FileSystem {
	public static final FileSystem NIO_FILE_SYSTEM__ = new NIOFileSystem();

	private NIOFileSystem() {
	}

	@Override
	public Directory findWorking() throws IOException {
		return NIOPath(Paths.get("."))
				.existingAsDirectory()
				.orElseThrow(() -> new IOException("The working directory doesn't exist."));
	}
}