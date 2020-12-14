package com.meti.exec.compile.render;

import com.meti.api.core.Option;

public class ImplicitType implements Type {
	public static final Type ImplicitType_ = new ImplicitType();

	private ImplicitType() {
	}

	@Override
	public Option<String> render(String name) {
		return null;
	}

	@Override
	public Option<String> findContent() {
		return null;
	}

	@Override
	public Group findGroup() {
		return null;
	}

	@Override
	public Option<String> render() {
		return null;
	}
}
