package com.meti.exec.compile.render.feature;

import com.meti.api.core.Option;
import com.meti.exec.compile.render.Node;

public interface Tokenizer {
	Option<Node> tokenize();
}
