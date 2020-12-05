package com.meti.api.io.file.nio;

import com.meti.api.io.InStream;
import com.meti.api.io.JavaInStream;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class NIOExtant implements Extant {
	private final Path path;

	public NIOExtant(Path path) {
		this.path = path;
	}

	@Override
	public InStream read() throws IOException {
		var inputStream = Files.newInputStream(path);
		return new JavaInStream(inputStream);
	}
}
