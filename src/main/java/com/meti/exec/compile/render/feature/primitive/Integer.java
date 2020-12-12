package com.meti.exec.compile.render.feature.primitive;

import com.meti.api.collect.string.Strings;
import com.meti.api.core.Primitives;
import com.meti.exec.compile.render.Node;

public class Integer implements Node<Integer> {
	private final int value;

	private Integer(int value) {
		this.value = value;
	}

	public static Integer Integer(int value) {
		return new Integer(value);
	}

	@Override
	public String render() {
		return Strings.valueOfInt(value);
	}

	@Override
	public int compareTo(Integer o) {
		return Primitives.comparingInts(value, o.value);
	}
}
