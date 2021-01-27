package com.meti.compile;

import com.meti.compile.token.Token;

public interface LexingStage {
	String lexField(String line);

	Token lexNode(String line);

	String lexType(String content);
}
