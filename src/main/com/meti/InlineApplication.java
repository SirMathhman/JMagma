package com.meti;

import java.io.IOException;

final record InlineApplication(Source source, Target target, Compiler compiler) implements Application {
	@Override
	public void execute() throws IOException {
		var input = source.read();
		var output = compiler.compile(input);
		target.write(output);
	}
}
