package com.meti.compile.stage;

import com.meti.api.magma.core.Option;
import com.meti.compile.token.Token;

public interface Renderer<T> {
	Option<Token> render(T token);
}
