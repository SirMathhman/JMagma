package com.meti.compile.io;

import com.meti.api.java.collect.JavaLists;
import com.meti.api.magma.io.Directory;
import com.meti.api.magma.io.File;
import com.meti.api.magma.io.IOException_;
import com.meti.compile.CompileException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public record DirectoryStorer(Directory root) implements Storer<List<File>> {
	@Override
	public List<File> write(Result result) throws IOException_, CompileException {
		var results = new ArrayList<File>();
		for (int i = 0; i < result.count(); i++) {
			var parent = root;
			var sourceSize = JavaLists.toJava(result.listSources()).get(i).size();
			for (int j = 0; j < sourceSize - 1; j++) {
				var path = parent.resolve(JavaLists.toJava(result.listSources()).get(i).apply(i));
				parent = path.ensureAsDirectory();
			}
			var name = JavaLists.toJava(result.listSources()).get(i).apply(sourceSize - 1);
			var option = result.apply(JavaLists.toJava(result.listSources()).get(i))
					.map(Optional::of)
					.orElseGet(Optional::empty);
			int finalI = i;
			var mapping = option.orElseThrow(() -> new CompileException("No result was found for: " + JavaLists.toJava(result.listSources()).get(finalI)));
			var formats = JavaLists.toJava(mapping.listFormats());
			var size = formats.size();
			for (int j = 0; j < size; j++) {
				var format = formats.get(j);
				var value = mapping.apply(format);
				var formattedName = format.format(name);
				var path = parent.resolve(formattedName)
						.ensureAsFile()
						.writeAsString(value);
				results.add(path);
			}
		}
		return results;
	}
}
