package com.meti.compile.stage;

import com.meti.compile.script.Script;
import com.meti.compile.token.Node;
import com.meti.compile.process.ParseException;

import java.util.List;

public interface ParsingStage {
	List<Node> process(Script script, List<Node> nodes) throws ParseException;
}
