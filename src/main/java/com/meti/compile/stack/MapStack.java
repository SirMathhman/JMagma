package com.meti.compile.stack;

import com.meti.api.magma.collect.Sequence;
import com.meti.compile.io.Source;

public class MapStack implements Stack {
	@Override
	public Stack reset(Sequence<Source> imports) {
		return this;
	}
}
