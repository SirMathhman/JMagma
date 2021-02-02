package com.meti.compile;

import com.meti.compile.token.Input;
import com.meti.compile.token.Output;

public interface Compiler {
	Output compile(Input input);
}
