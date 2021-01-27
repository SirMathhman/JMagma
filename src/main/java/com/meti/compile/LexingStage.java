package com.meti.compile;

import com.meti.compile.token.Content;

public interface LexingStage {
	String lexField(String line);

	Content lexNode(String line, Compiler compiler);

	String lexType(String content);
}
