package com.meti.compile;

import java.io.IOException;
import java.util.List;

public interface Target<C, R> {
	List<R> write(Script script, Result<C, ?> value) throws IOException;
}
