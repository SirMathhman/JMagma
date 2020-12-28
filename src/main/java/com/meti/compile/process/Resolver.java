package com.meti.compile.process;

import com.meti.api.core.Option;
import com.meti.compile.token.Node;
import com.meti.compile.token.Type;

public interface Resolver {
	Option<Type> resolve(Node node);
}
