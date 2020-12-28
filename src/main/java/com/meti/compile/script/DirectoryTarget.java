package com.meti.compile.script;

import com.meti.api.io.Directory;
import com.meti.api.io.File;
import com.meti.api.io.Path;
import com.meti.compile.Class;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public record DirectoryTarget<C extends Class>(Directory directory) implements Target<C, File> {
	private Path prepareDirectory(Script script) throws IOException {
		return script.listParent()
				.stream()
				.reduce(directory.asPath(), Path::resolve, (path, path2) -> path2)
				.ensureDirectories()
				.asPath();
	}

	@Override
	public List<File> write(Script script, Result<C, ?> value) throws IOException {
		var directory = prepareDirectory(script);
		var name = script.name();
		var files = new ArrayList<File>();
		var keys = value.list();
		for (int i = 0; i < keys.size(); i++) {
			var clazz = keys.get(i);
			var child = directory.resolve(clazz.format(name));
			var content = value.render(clazz);
			var nioFile = child.ensureAsFile().writeString(content);
			files.add(nioFile);
		}
		return files;
	}
}