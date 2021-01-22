package com.meti.compile.stack;

import com.meti.api.java.collect.JavaLists;
import com.meti.compile.io.Source;

import java.util.List;

public class MapStack implements Stack {
	@Override
	public Stack reset(com.meti.api.magma.collect.List<Source> imports) {
		return reset1(JavaLists.toJava(imports));
	}

	private Stack reset1(List<Source> imports) {
		return this;
	}
}
