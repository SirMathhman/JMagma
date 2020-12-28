package com.meti.compile;

import com.meti.compile.feature.Node;

import java.util.List;

public interface Result<C, G> {
	Result<C, G> put(C header, G include, Node node);

	List<C> list();

	String render(C clazz);
}
