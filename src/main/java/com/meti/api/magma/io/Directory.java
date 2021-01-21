package com.meti.api.magma.io;

import java.util.stream.Stream;

public interface Directory extends Path {
	Path delete() throws IOException_;

	Path relativize(Path other) throws IOException_;

	Path resolve(String name);

	Stream<Path> streamTree() throws IOException_;
}
