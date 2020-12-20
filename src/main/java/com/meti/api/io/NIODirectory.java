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
}
