package com.meti.compile;

import java.util.List;

public interface Result<T> {
	List<T> listKeys();

	String renderToString(T type);
}
