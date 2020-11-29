package com.meti;

import com.meti.api.core.Option;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Map;

public interface Cache<E> {
	Map<CCache.Group, Path> write(Path parent, String name) throws IOException;

	com.meti.api.collect.Map<CCache.Group, Path> write2(Path parent, String name) throws IOException;

	Option<String> retrieve(E group);
}
