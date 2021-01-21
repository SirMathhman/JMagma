package com.meti.compile;

import com.meti.api.magma.io.IOException_;
import com.meti.api.magma.io.Path;
import com.meti.compile.io.DirectoryLoader;
import com.meti.compile.io.DirectoryStorer;

import static com.meti.api.java.io.NIOFileSystem.NIOFileSystem_;

record Application(Path root) {
	static final Application Application_ = new Application(NIOFileSystem_.Root());

	void run() throws IOException_, CompileException {
		var rootAsDirectory = this.root.ensureAsDirectory();
		var loader = new DirectoryLoader(rootAsDirectory);
		var result = MagmaCCompiler.MagmaCCompiler_.compile(loader);
		new DirectoryStorer(rootAsDirectory).write(result);
	}
}