package com.meti.exec.compile;

class MagmaCompiler implements Compiler {
	static final Compiler Compiler_ = new MagmaCompiler();

	private MagmaCompiler() {
	}

	@Override
	public Result<Result.Group> compiler(String content) throws CompileException {
		return new MapResult().with(Result.Group.Target, "int main(){return 0;}");
	}
}
