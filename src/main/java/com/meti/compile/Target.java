package com.meti.compile;

import java.io.IOException;
import java.util.List;

public interface Target<T, R> {
	List<R> write(Script script, Result<T> value) throws IOException;
}
