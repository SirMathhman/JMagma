package com.meti.api.io;

import java.io.IOException;
import java.nio.file.Files;
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
			deleteImpl(path);
		}
		return this;
	}

	private void deleteImpl(java.nio.file.Path path) throws IOException {
		if (Files.isDirectory(path)) {
			var children = Files.list(path).collect(Collectors.toList());
			for (java.nio.file.Path child : children) {
				deleteImpl(child);
			}
		}
		Files.delete(path);
	}

	@Override
	public Path deleteAll() throws IOException {
		deleteImpl(value);
		return new NIOPath(value);
	}
}
