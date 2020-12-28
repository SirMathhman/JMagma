package com.meti.compile;

import com.meti.compile.feature.Node;
import com.meti.compile.process.ParseException;

import java.util.List;

public interface ParsingStage {
	List<Node> process(Script script, List<Node> nodes) throws ParseException;
}
