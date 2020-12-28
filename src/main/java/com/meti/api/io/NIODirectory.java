package com.meti.api.io;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class NIODirectory implements Directory {
	private final java.nio.file.Path value;

	public NIODirectory(java.nio.file.Path value) {
		this.value = value;
	}

	@Override
	public List<Path> list() throws IOException {
		return Files.list(value)
				.map(NIOPath::new)
				.collect(Collectors.toList());
	}

	@Override
	public List<Path> walk() throws IOException {
		var list = new ArrayList<Path>();
		var children = list();
		for (Path child : children) {
			list.addAll(child.existingAsDirectory()
					.mapExceptionally(Directory::walk)
					.orElse(Collections.singletonList(child)));
		}
		return list;
	}

	@Override
	public Path asPath() {
		return new NIOPath(value);
	}

	@Override
	public Directory deleteChildren() throws IOException {
		var list = Files.list(value).collect(Collectors.toList());
		for (java.nio.file.Path path : list) {
			Files.walkFileTree(path, new DeleteVisitor());
		}
		return this;
	}

	@Override
	public Path deleteAll() throws IOException {
		deleteChildren();
		Files.deleteIfExists(value);
		return new NIOPath(value);
	}

	private static class DeleteVisitor implements FileVisitor<java.nio.file.Path> {
		@Override
		public FileVisitResult preVisitDirectory(java.nio.file.Path dir, BasicFileAttributes attrs) throws IOException {
			return FileVisitResult.CONTINUE;
		}

		@Override
		public FileVisitResult visitFile(java.nio.file.Path file, BasicFileAttributes attrs) throws IOException {
			Files.delete(file);
			return FileVisitResult.CONTINUE;
		}

		@Override
		public FileVisitResult visitFileFailed(java.nio.file.Path file, IOException exc) throws IOException {
			return FileVisitResult.CONTINUE;
		}

		@Override
		public FileVisitResult postVisitDirectory(java.nio.file.Path dir, IOException exc) throws IOException {
			Files.delete(dir);
			return FileVisitResult.CONTINUE;
		}
	}
}
