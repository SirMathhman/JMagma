package com.meti.compile;

import com.meti.compile.path.ScriptPath;
import com.meti.compile.tokenize.slice.BracketSplitter;

import java.util.stream.Collectors;

public class MagmaCompiler implements Compiler {
    private final Stage<String, Node> stage;

    public MagmaCompiler(ScriptPath scriptPath) {
        stage = new TokenizerStage(scriptPath);
    }

    public static Compiler MagmaCompiler(ScriptPath scriptPath) {
        return new MagmaCompiler(scriptPath);
    }

    @Override
    public String compile(String value) {
        return new BracketSplitter(value)
                .split()
                .map(stage::apply)
                .map(Node::render)
                .collect(Collectors.joining());
    }
}