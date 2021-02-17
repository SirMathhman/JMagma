package com.meti.output;

import com.meti.F1E1;
import com.meti.Token;

public class NodeOutput extends TokenOutput {
	public NodeOutput(Token token) {
		super(token);
	}

	@Override
	public <E extends Exception> Output replaceNode(F1E1<Token, String, E> replacer) throws E {
		return new StringOutput(replacer.apply(token));
	}
}
