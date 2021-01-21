package com.meti.api.magma.io;

import com.meti.api.java.collect.JavaList;

import java.util.List;

public interface Directory extends Path {
	Path delete() throws IOException_;

	default com.meti.api.magma.collect.List<Path> listTree() throws IOException_ {
		return new JavaList<Path>(listTree1());
	}

	default List<Path> listTree1() throws IOException_ {
		throw new UnsupportedOperationException();
	}

	Path relativize(Path other) throws IOException_;

	Path resolve(String name);
}
