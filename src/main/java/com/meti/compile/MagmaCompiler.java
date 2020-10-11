package com.meti.compile;

import com.meti.compile.render.node.ContentNode;
import com.meti.compile.render.node.Node;
import com.meti.compile.render.process.State;

import java.util.stream.Collectors;

import static com.meti.compile.FormattingStage.FormattingStage;
import static com.meti.compile.ParsingStage.ParsingStage;
import static com.meti.compile.TokenizerStage.TokenizerStage;
import static com.meti.compile.render.process.InlineState.State;
import static com.meti.compile.render.process.MappedStack.Stack_;

public class MagmaCompiler implements Compiler {
    public static final Compiler Compiler_ = new MagmaCompiler();

    @Deprecated
    public MagmaCompiler() {
    }

    @Override
    public String compile(String content) {
        var tree = tokenize(content);
        var state = State(tree, Stack_);
        var formatted = FormattingStage.apply(state);
        var parsed = ParsingStage.apply(formatted);
        return render(parsed);
    }

    private Node tokenize(String content) {
        var trim = content.trim();
        var root = new ContentNode(trim);
        return TokenizerStage.apply(root);
    }

    private String render(State parsed) {
        var renderedStructures = parsed.streamStructures().map(Node::render).collect(Collectors.joining(""));
        var renderedFunctions = parsed.streamFunctions().map(Node::render).collect(Collectors.joining(""));
        return renderedStructures + renderedFunctions + parsed.getValue().render();
    }
}
