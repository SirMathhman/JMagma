package com.meti.compile;

import com.meti.api.magma.io.IOException_;
import com.meti.api.magma.io.Path;
import com.meti.compile.io.DirectoryLoader;
import com.meti.compile.io.DirectoryStorer;

import static com.meti.api.java.io.NIOFileSystem.NIOFileSystem_;
import static com.meti.compile.MagmaCCompiler.MagmaCCompiler_;

record Application(Path root) {
	static final Application Application_ = new Application(NIOFileSystem_.Root());

	void run() throws IOException_, CompileException {
		var rootAsDirectory = this.root.ensureAsDirectory();
		var loader = new DirectoryLoader(rootAsDirectory);
		var result = MagmaCCompiler_.compile(loader);
		var storer = new DirectoryStorer(rootAsDirectory);
		storer.write(result);
	}
}