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
		var path = root.asPath();
		var reduce = script.streamAll().reduce(path, Path::resolve, (path1, path2) -> path2);
		return reduce.existingAsFile().mapExceptionally(File::readString);
	}

	@Override
	public List<Script> list() throws IOException {
		var absolute = root.asPath().asAbsolute();
		var rootNames = absolute.names();
		var files = root.walk();
		var packages = new ArrayList<Script>();
		for (Path file : files) {
			if (file.hasExtensionOf("mg")) {
				var pathNames = file.asAbsolute().names();
				for (String rootName : rootNames) {
					pathNames.remove(rootName);
				}
				var last = pathNames.get(pathNames.size() - 1);
				var period = last.indexOf('.');
				var slice = last.substring(period + 1);
				var name = slice.trim();
				pathNames.add(name);
				var script = new ListScript(pathNames);
				packages.add(script);
			}
		}
		return packages;
	}
}
