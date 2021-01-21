package com.meti.api.magma.io;

import com.meti.api.java.collect.JavaList;
import com.meti.api.magma.collect.List;

public interface Directory extends Path {
	Path delete() throws IOException_;

	@Override
	default List<String> listNames() {
		return new JavaList<>(listNames1());
	}

	default private java.util.List<String> listNames1() {
		throw new UnsupportedOperationException();
	}

	List<Path> listTree() throws IOException_;

	Path relativize(Path other) throws IOException_;

	Path resolve(String name);
}
