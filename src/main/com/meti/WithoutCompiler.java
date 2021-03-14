package com.meti;

record WithoutCompiler(FileSource source, Target target) {
	Application complete(Compiler compiler) {
		return new InlineApplication(source, target, compiler);
	}
}
