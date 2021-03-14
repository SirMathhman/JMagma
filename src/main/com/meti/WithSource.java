package com.meti;

record WithSource(FileSource source) {
	static WithSource Application(FileSource source) {
		return new WithSource(source);
	}

	WithoutCompiler withTarget(Target target) {
		return new WithoutCompiler(source, target);
	}
}
