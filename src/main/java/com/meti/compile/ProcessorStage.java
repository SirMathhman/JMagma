package com.meti.compile;

import com.meti.compile.feature.Node;
import com.meti.compile.process.ProcessException;

import java.util.List;

public interface ProcessorStage {
	List<Node> process(Script script, List<Node> nodes) throws ProcessException;
}
