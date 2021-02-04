package com.meti.compile.content;

import com.meti.api.magma.collect.stream.Stream;
import com.meti.compile.token.Input;

public interface Splitter {
	Stream<Input> stream(Input input);
}
