package com.meti.compile;

import com.meti.api.core.Option;
import com.meti.api.io.Directory;
import com.meti.api.io.File;
import com.meti.api.io.Path;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public record DirectorySource(Directory root) implements Source {
	@Override
	public Option<String> read(Script script) throws IOException {
		return script.streamParent()
				.reduce(root.asPath(), Path::resolve, (path1, path2) -> path2)
				.resolve(script.name() + ".mg")
				.existingAsFile()
				.mapExceptionally(File::readString);
	}

	@Override
	public List<Script> list() throws IOException {
		var absolute = root.asPath().asAbsolute();
		var rootNames = absolute.names();
		var files = root.walk();
		var packages = new ArrayList<Script>();
		for (Path file : files) {
			if (file.hasExtensionOf("mg")) {
				var parent = file.asAbsolute().names();
				for (String rootName : rootNames) {
					parent.remove(rootName);
				}
				var last = parent.remove(parent.size() - 1);
				var period = last.indexOf('.');
				var slice = last.substring(0, period);
				var name = slice.trim();
				var script = new ListScript(parent, name);
				packages.add(script);
			}
		}
		return packages;
	}
}
