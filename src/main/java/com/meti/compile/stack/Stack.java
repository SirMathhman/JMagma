package com.meti.compile.stack;

import com.meti.api.magma.collect.Sequence;
import com.meti.compile.io.Source;

public interface Stack {
	Stack reset(Sequence<Source> imports);
}
