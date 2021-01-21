package com.meti.api.java.io;

import com.meti.api.magma.collect.IndexException;
import com.meti.api.magma.io.FileSystem;
import com.meti.api.magma.io.Path;

import java.nio.file.Paths;

public class NIOFileSystem implements FileSystem {
	public static final FileSystem NIOFileSystem_ = new NIOFileSystem();

	private NIOFileSystem() {
	}

	@Override
	public Path Root() {
		return new NIOPath(Paths.get("."));
	}

	@Override
	public Path absolute(String... args) throws IndexException {
		var argsLength = args.length;
		if (argsLength == 0) throw new IndexException("No items were given.");
		var first = args[0];
		var moreLength = argsLength - 1;
		if (moreLength == 0) {
			return new NIOPath(Paths.get(first));
		} else {
			var more = new String[moreLength];
			for (int i = 0; i < moreLength; i++) more[i] = args[i + 1];
			return new NIOPath(Paths.get(first, more));
		}
	}
}