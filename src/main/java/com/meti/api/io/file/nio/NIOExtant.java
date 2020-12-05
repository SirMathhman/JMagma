package com.meti.api.io.file.nio;

import com.meti.api.io.InStream;
import com.meti.api.io.JavaInStream;
import com.meti.api.io.JavaOutStream;
import com.meti.api.io.OutStream;
import com.meti.api.io.file.Extant;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class NIOExtant implements Extant {
	private final Path path;

	private NIOExtant(Path path) {
		this.path = path;
	}

	public static NIOExtant NIOExtant(Path path) {
		return new NIOExtant(path);
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
