package com.meti;

public class NodeOutput extends TokenOutput {
	NodeOutput(Token token) {
		super(token);
	}

	@Override
	public <E extends Exception> Output replaceNode(F1E1<Token, String, E> replacer) throws E {
		return new StringOutput(replacer.apply(token));
	}
}
