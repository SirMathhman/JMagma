package com.meti.compile.stage;

import com.meti.compile.token.TokenizationException;
import com.meti.compile.token.Node;

import java.util.List;

public interface TokenizationStage {
	Node tokenizeSingle(String content) throws TokenizationException;

	List<Node> tokenizeAll(String content) throws TokenizationException;
}
