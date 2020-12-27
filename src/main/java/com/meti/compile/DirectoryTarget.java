package com.meti.compile;

import com.meti.api.io.Directory;
import com.meti.api.io.File;
import com.meti.api.io.Path;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public record DirectoryTarget<T extends TargetType>(Directory directory) implements Target<T, File> {
	private Path formatDirectory(Script script) {
		return script.listParent()
				.stream()
				.reduce(directory.asPath(), Path::resolve, (path, path2) -> path2);
	}

	@Override
	public List<File> write(Script script, Result<T> value) throws IOException {
		var directory = formatDirectory(script);
		var name = script.name();
		var files = new ArrayList<File>();
		var keys = value.listKeys();
		for (int i = 0; i < keys.size(); i++) {
			var type = keys.get(i);
			var child = directory.resolve(type.format(name));
			var content = value.renderToString(type);
			var nioFile = child.ensureAsFile().writeString(content);
			files.add(nioFile);
		}
		return files;
	}
}