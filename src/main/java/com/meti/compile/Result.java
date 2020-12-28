package com.meti.compile;

import com.meti.compile.feature.Node;

import java.util.List;

public interface Result<C, G> {
	boolean has(C clazz);

	Result<C, G> put(C clazz, G group, Node node);

	List<C> list();

	String render(C clazz);
}
