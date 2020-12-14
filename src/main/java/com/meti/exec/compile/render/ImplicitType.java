package com.meti.exec.compile.render;

import com.meti.api.core.None;
import com.meti.api.core.Option;
import com.meti.api.core.Some;

import static com.meti.api.core.None.None;
import static com.meti.api.core.Some.Some;

public class ImplicitType implements Type {
	public static final Type ImplicitType_ = new ImplicitType();

	private ImplicitType() {
	}

	@Override
	public Option<String> render(String name) {
		return Some("? " + name);
	}

	@Override
	public Option<String> findContent() {
		return None();
	}

	@Override
	public Group findGroup() {
		return Group.Implicit;
	}
}
