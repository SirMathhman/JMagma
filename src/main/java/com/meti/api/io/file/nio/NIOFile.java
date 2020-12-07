package com.meti.api.io.file.nio;

import com.meti.api.io.InStream;
import com.meti.api.io.JavaInStream;
import com.meti.api.io.JavaOutStream;
import com.meti.api.io.OutStream;
import com.meti.api.io.file.File;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class NIOFile implements File {
	private final Path path;

	private NIOFile(Path path) {
		this.path = path;
	}

	public static NIOFile NIOExtant(Path path) {
		return new NIOFile(path);
	}

	@Override
	public InStream read() throws IOException {
		return new JavaInStream(Files.newInputStream(path));
	}

	@Override
	public OutStream write() throws IOException {
		return new JavaOutStream(Files.newOutputStream(path));
	}
}
