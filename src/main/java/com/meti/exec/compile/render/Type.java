package com.meti.exec.compile.render;

public interface Type extends Renderable {
	String render(String name) throws RenderException;

	@Override
	default String render() throws RenderException {
		return render("");
	}
}
