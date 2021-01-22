package com.meti.api.magma.io;

import com.meti.api.magma.collect.IndexException;

public interface FileSystem {
	Path root();

	Path absolute(String... args) throws IndexException;
}
