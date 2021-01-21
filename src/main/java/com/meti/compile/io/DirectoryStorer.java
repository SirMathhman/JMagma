package com.meti.compile.io;

import com.meti.api.magma.io.Directory;
import com.meti.api.magma.io.IOException_;
import com.meti.api.magma.io.Path;
import com.meti.compile.CompileException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public record DirectoryStorer(Directory root) implements Storer<List<Path>> {
	@Override
	public List<Path> write(Result result) throws IOException_, CompileException {
		var sources = result.listSources();
		var results = new ArrayList<Path>();
		for (int i = 0; i < sources.size(); i++) {
			var source = sources.get(i);
			var parent = root;
			var sourceSize = source.size();
			for (int j = 0; j < sourceSize - 1; j++) {
				var path = parent.resolve(source.apply(i));
				parent = path.ensureAsDirectory();
			}
			var name = source.apply(sourceSize - 1);
			var option = result.apply(source);
			var mapping = option.orElseThrow(() -> new CompileException("No result was found for: " + source));
			var formats = mapping.listFormats();
			var size = formats.size();
			for (int j = 0; j < size; j++) {
				var format = formats.get(i);
				var value = mapping.apply(format);
				var formattedName = format.format(name);
				var path = parent.resolve(formattedName)
						.ensureAsFile()
						.writeAsString1(value);
				results.add(path);
			}
		}
		return results;
	}
}
