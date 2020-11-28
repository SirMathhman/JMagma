package com.meti;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

import static com.meti.CCache.Group.*;
import static com.meti.Some.Some;

public class InlineCache implements CCache {
	private final String sourceString;
	private final String headerString;
	private final String reflectionString;

	public InlineCache(String sourceString, String headerString, String reflectionString) {
		this.sourceString = sourceString;
		this.headerString = headerString;
		this.reflectionString = reflectionString;
	}

	@Override
	public Option<String> retrieve(Group group) {
		return switch (group) {
			case NativeSource -> Some(sourceString);
			case NativeHeader -> Some(headerString);
			case SourceHeader -> Some(reflectionString);
		};
	}

	private Map<Group, Path> writeGroup(Path sourcePath, Group group, String name) throws IOException {
		Path sibling = sourcePath.resolveSibling(name);
		String output = retrieve(group).orElse("");
		Files.writeString(sibling, output);
		return Map.of(group, sibling);
	}

	private com.meti.Map<Group, Path> writeGroup2(Path sourcePath, Group group, String name) throws IOException {
		Path sibling = sourcePath.resolveSibling(name);
		String output = retrieve(group).orElse("");
		Files.writeString(sibling, output);
		return SingletonMap.SingletonMap(group, sibling);
	}

	@Override
	public Map<Group, Path> write(Path parent, String name) throws IOException {
		Map<Group, Path> written = new HashMap<>();
		written.putAll(writeGroup(parent, NativeSource, "%s.c".formatted(name)));
		written.putAll(writeGroup(parent, NativeHeader, "%s.h".formatted(name)));
		written.putAll(writeGroup(parent, SourceHeader, "%s.mgh".formatted(name)));
		return written;
	}

	@Override
	public com.meti.Map<Group, Path> write2(Path parent, String name) throws IOException {
		return new ListMap<Group, Path>()
				.putAll(writeGroup2(parent, NativeSource, "%s.c".formatted(name)))
				.putAll(writeGroup2(parent, NativeHeader, "%s.h".formatted(name)))
				.putAll(writeGroup2(parent, SourceHeader, "%s.mgh".formatted(name)));
	}
}
