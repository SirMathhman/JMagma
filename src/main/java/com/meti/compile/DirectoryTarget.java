package com.meti.compile;

import com.meti.api.io.Directory;
import com.meti.api.io.File;
import com.meti.api.io.Path;

import java.io.IOException;

public record DirectoryTarget(Directory directory) implements Target<File> {
	@Override
	public File write(Script script, String value) throws IOException {
		var name = script.name();
		return script.streamParents()
				.reduce(directory.asPath(), Path::resolve, (path, path2) -> path2)
				.resolve(name + ".c")
				.ensureAsFile()
				.writeString(value);
	}
}