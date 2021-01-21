package com.meti.compile.stack;

import com.meti.compile.io.Source;

import java.util.List;

public class MapStack implements Stack {
	@Override
	public Stack reset(List<Source> imports) {
		return this;
	}
}
