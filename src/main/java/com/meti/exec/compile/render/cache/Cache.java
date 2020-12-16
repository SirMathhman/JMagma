package com.meti.exec.compile.render.cache;

import com.meti.exec.compile.render.Node;
import com.meti.exec.compile.render.Renderable;

public interface Cache<T> extends Renderable {
	Cache<T> with(T key, Node node);

	@Override
	default String asString() {
		return "";
	}
}
