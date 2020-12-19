package com.meti.compile.feature.function;

import com.meti.compile.feature.Type;

public class VarArgsType implements Type {
	public static final Type VarArgsType_ = new VarArgsType();

	public VarArgsType() {
	}

	@Override
	public String render(String value) {
		return "...";
	}
}
