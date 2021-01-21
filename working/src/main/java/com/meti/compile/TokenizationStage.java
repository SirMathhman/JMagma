package com.meti.compile;

import com.meti.compile.feature.Node;

import java.util.List;

public interface TokenizationStage {
	Node tokenizeSingle(String content) throws TokenizationException;

	List<Node> tokenizeAll(String content) throws TokenizationException;
}
