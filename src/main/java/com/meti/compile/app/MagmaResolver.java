package com.meti.compile.app;

import com.meti.compile.ResolutionException;
import com.meti.compile.attribute.AttributeException;
import com.meti.compile.integer.IntegerType;
import com.meti.compile.parse.State;
import com.meti.compile.token.Token;
import com.meti.compile.token.Tokens;

public class MagmaResolver {
	public static MagmaResolver MagmaResolver_ = new MagmaResolver();

	public MagmaResolver() {
	}

	public Token resolve(State state) throws ResolutionException {
		try {
			if (Tokens.is(state.getCurrent(), Token.Type.Integer)) {
				return IntegerType.signed(16);
			}
			throw new ResolutionException("Cannot resolve: " + state.getCurrent());
		} catch (AttributeException e) {
			throw new ResolutionException(e);
		}
	}
}
