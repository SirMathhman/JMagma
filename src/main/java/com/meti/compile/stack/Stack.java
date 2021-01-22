package com.meti.compile.stack;

import com.meti.api.magma.collect.List;
import com.meti.compile.io.Source;

public interface Stack {
	Stack reset(List<Source> imports);
}
