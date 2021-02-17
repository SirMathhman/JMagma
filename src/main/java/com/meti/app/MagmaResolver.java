package com.meti.app;

import com.meti.ResolutionException;
import com.meti.attribute.AttributeException;
import com.meti.integer.IntegerType;
import com.meti.parse.State;
import com.meti.token.Token;
import com.meti.token.Tokens;

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
