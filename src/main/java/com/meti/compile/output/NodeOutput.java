package com.meti.compile.output;

import com.meti.core.F1E1;
import com.meti.compile.token.Token;

public class NodeOutput extends TokenOutput {
	public NodeOutput(Token token) {
		super(token);
	}

	@Override
	public <E extends Exception> Output replaceNode(F1E1<Token, String, E> replacer) throws E {
		return new StringOutput(replacer.apply(token));
	}
}
