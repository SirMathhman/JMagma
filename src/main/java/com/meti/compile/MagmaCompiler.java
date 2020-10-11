package com.meti.compile;

import com.meti.compile.render.node.ContentNode;
import com.meti.compile.render.node.Node;
import com.meti.compile.render.process.*;

import java.util.stream.Collectors;

import static com.meti.compile.TokenizerStage.TokenizerStage_;

public class MagmaCompiler implements Compiler {
    public static final Compiler Compiler_ = new MagmaCompiler();

    @Deprecated
    public MagmaCompiler() {
    }

    @Override
    public String compile(String content) {
        var trim = content.trim();
        var root = new ContentNode(trim);
        var tree = TokenizerStage_.apply(root);
        var stack = new MappedStack();
        var state = InlineState.State(tree, stack);
        var formatted = new Formatter(state).process().orElse(state);
        var parsed = new Parser(formatted).process().orElse(formatted);
        return render(parsed);
    }

    private String render(State parsed) {
        var renderedStructures = parsed.streamStructures().map(Node::render).collect(Collectors.joining(""));
        var renderedFunctions = parsed.streamFunctions().map(Node::render).collect(Collectors.joining(""));
        return renderedStructures + renderedFunctions + parsed.getValue().render();
    }
}
