package com.meti.exec.compile.render;

import static com.meti.exec.compile.render.RenderException.RenderException;

public class ImplicitType implements Type {
	public static final Type ImplicitType_ = new ImplicitType();

	private ImplicitType() {
	}

	@Override
	public String render(String name) throws RenderException {
		throw RenderException("Implicit types aren't renderable and have to be removed by the compiler.");
	}
}
