package com.meti.api.magma.io;

import com.meti.api.magma.collect.Sequence;

public interface Directory extends Path {
	Path delete() throws IOException_;

	Sequence<Path> listTree() throws IOException_;

	Path relativize(Path other) throws IOException_;

	Path resolve(String name);
}
