package com.meti.compile.render.block.invoke;

import com.meti.compile.render.node.ContentNode;
import com.meti.compile.render.node.Node;
import com.meti.compile.render.tokenize.AbstractNodeTokenizer;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;

public class InvocationTokenizer extends AbstractNodeTokenizer {
    public InvocationTokenizer(String content) {
        super(content);
    }

    @Override
    public Optional<Node> evaluate() {
        if (content.contains("(") && content.endsWith(")")) {
            var callerString = content.substring(0, content.indexOf('(')).trim();
            var caller = new ContentNode(callerString);
            var argumentString = content.substring(content.indexOf('(') + 1, content.length() - 1).trim();
            var arguments = Arrays.stream(argumentString.split(","))
                    .filter(s -> !s.isBlank())
                    .map(String::trim)
                    .map(ContentNode::new)
                    .collect(Collectors.toList());
            return Optional.of(new Mapping(caller, arguments));
        }
        return Optional.empty();
    }
}
