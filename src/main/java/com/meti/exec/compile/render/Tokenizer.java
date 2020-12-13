package com.meti.exec.compile.render;

import com.meti.api.core.Option;
import com.meti.exec.compile.render.Node;

public interface Tokenizer<T> {
	Option<T> tokenize() throws TokenizationException;
}
