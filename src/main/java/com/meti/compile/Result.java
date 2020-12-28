package com.meti.compile;

import com.meti.compile.feature.Node;

import java.util.List;

public interface Result<C, G> {
	Result<C, G> put(Node node, C header, G include);

	List<C> listClasses();

	String render(C clazz);
}
