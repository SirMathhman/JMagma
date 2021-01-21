package com.meti.api.java.io;

import com.meti.api.magma.io.File;
import com.meti.api.magma.io.IOException_;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public record NIOFile(Path path) implements File {
	@Override
	public com.meti.api.magma.io.Path delete() throws IOException_ {
		try {
			Files.delete(path);
			return new NIOPath(path);
		} catch (IOException e) {
			throw new IOException_(e);
		}
	}

	@Override
	public String readAsString() throws IOException_ {
		try {
			return Files.readString(path);
		} catch (IOException e) {
			throw new IOException_(e);
		}
	}

	@Override
	public File writeAsString(String content) throws IOException_ {
		try {
			Files.writeString(path, content);
			return this;
		} catch (IOException e) {
			throw new IOException_(e);
		}
	}

}
