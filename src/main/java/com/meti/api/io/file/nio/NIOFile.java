package com.meti.api.io.file.nio;

import com.meti.api.io.InStream;
import com.meti.api.io.JavaInStream;
import com.meti.api.io.JavaOutStream;
import com.meti.api.io.OutStream;
import com.meti.api.io.file.File;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static com.meti.api.io.file.nio.NIOPath.NIOPath;

public class NIOFile implements File {
	private final Path path;

	private NIOFile(Path path) {
		this.path = path;
	}

	public static NIOFile NIOFile(Path path) {
		return new NIOFile(path);
	}

	@Override
	public com.meti.api.io.file.Path asPath() {
		return NIOPath(path);
	}

	@Override
	public InStream read() throws IOException {
		return new JavaInStream(Files.newInputStream(path));
	}

	@Override
	public OutStream write() throws IOException {
		return new JavaOutStream(Files.newOutputStream(path));
	}

	@Override
	public int compareTo(File o) {
		return asPath().compareTo(o.asPath());
	}
}
