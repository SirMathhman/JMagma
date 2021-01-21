package com.meti.compile.stage;

import com.meti.compile.token.Token;

import java.util.Optional;

public interface Renderer<T> {
	Optional<Token> render(T token);
}
