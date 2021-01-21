package com.meti.api.java.io;

import com.meti.api.magma.collect.IndexException;
import com.meti.api.magma.io.Directory;
import com.meti.api.magma.io.IOException_;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.meti.api.java.io.NIOFileSystem.NIOFileSystem_;

public final class NIODirectory extends NIOPath implements Directory {
	public NIODirectory(Path value) {
		super(value);
	}

	@Override
	public com.meti.api.magma.io.Path delete() throws IOException_ {
		try {
			Files.walkFileTree(value, new DeletingVisitor());
			return new NIOPath(value);
		} catch (IOException e) {
			throw new IOException_(e);
		}
	}

	@Override
	public List<com.meti.api.magma.io.Path> listTree1() throws IOException_ {
		return streamTree().collect(Collectors.toList());
	}

	@Override
	public com.meti.api.magma.io.Path relativize(com.meti.api.magma.io.Path other) throws IOException_ {
		var otherNames = other.listNames();

		var thisSize = value.getNameCount();
		var otherSize = otherNames.size();
		var length = otherSize - thisSize;
		if (length == 0) {
			var format = "The path to relativize '%s', and this directory '%s', are equivalent.";
			var message = format.formatted(other, this);
			throw new IOException_(message);
		}

		var newNames = new String[length];
		for (int i = 0; i < length; i++) {
			newNames[i] = otherNames.get(thisSize + i);
		}
		try {
			return NIOFileSystem_.absolute(newNames);
		} catch (IndexException e) {
			throw new IOException_("Failed to relativize %s on top of %s".formatted(other, value), e);
		}
	}

	@Override
	public com.meti.api.magma.io.Path resolve(String name) {
		return new NIOPath(value.resolve(name));
	}

	private Stream<com.meti.api.magma.io.Path> streamTree() throws IOException_ {
		try {
			return Files.walk(value).map(NIOPath::new);
		} catch (IOException e) {
			throw new IOException_(e);
		}
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this) return true;
		if (obj == null || obj.getClass() != this.getClass()) return false;
		var that = (NIODirectory) obj;
		return Objects.equals(this.value, that.value);
	}

	@Override
	public int hashCode() {
		return Objects.hash(value);
	}

	private static class DeletingVisitor implements FileVisitor<Path> {
		@Override
		public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
			return FileVisitResult.CONTINUE;
		}

		@Override
		public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
			Files.deleteIfExists(file);
			return FileVisitResult.CONTINUE;
		}

		@Override
		public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
			return FileVisitResult.CONTINUE;
		}

		@Override
		public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
			Files.deleteIfExists(dir);
			return FileVisitResult.CONTINUE;
		}
	}
}
