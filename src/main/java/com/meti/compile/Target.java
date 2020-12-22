package com.meti.compile;

import java.io.IOException;

public interface Target<T> {
	T write(Script script, String value) throws IOException;
}
