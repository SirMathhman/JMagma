package com.meti;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Map;

public interface Cache<E> {
	Map<CCache.Group, Path> write(Path parent, String name) throws IOException;

	com.meti.Map<CCache.Group, Path> write2(Path parent, String name) throws IOException;

	Option<String> retrieve(E group);
}
