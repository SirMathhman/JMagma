package com.meti.compile.token;

import java.util.List;

public record TokenListAttribute(List<Token> list) implements Attribute {
	@Override
	public List<Token> asTokenList() {
		return list;
	}
}
