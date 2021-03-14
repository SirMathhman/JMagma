package com.meti.compile.app;

import com.meti.compile.source.FileSource;
import com.meti.compile.source.Source;
import com.meti.compile.target.Target;

import java.io.IOException;

public final record InlineApplication(Source source, Target target, Compiler compiler) implements Application {
	public static WithSource InlineApplication(FileSource source) {
		return new WithSource(source);
	}

	@Override
	public void execute() throws IOException {
		var input = source.read();
		var output = compiler.compile(input);
		target.write(output);
	}

	public static record WithoutCompiler(FileSource source, Target target) {
		public Application complete(Compiler compiler) {
			return new InlineApplication(source, target, compiler);
		}
	}

	public static record WithSource(FileSource source) {
		public WithoutCompiler withTarget(Target target) {
			return new WithoutCompiler(source, target);
		}
	}
}
