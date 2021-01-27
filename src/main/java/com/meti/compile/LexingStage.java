package com.meti.compile;

import com.meti.compile.token.Field;
import com.meti.compile.token.Token;

public interface LexingStage {
	Field lexField(String line);

	Token lexNode(String line);

	Token lexType(String content);
}
