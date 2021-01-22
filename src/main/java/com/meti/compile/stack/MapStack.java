package com.meti.compile.stack;

import com.meti.api.magma.collect.List;
import com.meti.compile.io.Source;

public class MapStack implements Stack {
	@Override
	public Stack reset(List<Source> imports) {
		return this;
	}
}
