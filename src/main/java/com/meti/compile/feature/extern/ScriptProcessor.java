package com.meti.compile.feature.extern;

import com.meti.compile.Script;
import com.meti.compile.feature.Node;
import com.meti.compile.process.ProcessException;

public interface ScriptProcessor {
	Node process(Script current, Node node) throws ProcessException;
}
