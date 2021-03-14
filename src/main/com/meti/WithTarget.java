package com.meti;

record WithTarget(String target) {
	static WithTarget EqualityCompiler(String target) {
		return new WithTarget(target);
	}

	Compiler complete(String output) {
		return new EqualityCompiler(target, output);
	}
}
