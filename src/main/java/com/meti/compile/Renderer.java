package com.meti.compile;

import com.meti.api.core.Option;
import com.meti.compile.token.Output;
import com.meti.compile.token.Token;

public interface Renderer {
	Option<Output> render(Token token);
}
