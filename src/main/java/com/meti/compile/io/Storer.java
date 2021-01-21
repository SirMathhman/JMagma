package com.meti.compile.io;

import com.meti.api.magma.io.IOException_;

public interface Storer<T> {
	T write(Result result) throws IOException_;
}
