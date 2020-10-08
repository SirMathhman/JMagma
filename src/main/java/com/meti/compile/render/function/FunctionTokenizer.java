package com.meti.compile.render.function;

import com.meti.compile.render.evaluate.AbstractNodeTokenizer;
import com.meti.compile.render.field.Field;
import com.meti.compile.render.field.FieldTokenizer;
import com.meti.compile.render.node.ContentNode;
import com.meti.compile.render.node.Node;
import com.meti.compile.render.type.ContentType;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;

public class FunctionTokenizer extends AbstractNodeTokenizer {
    public FunctionTokenizer(String content) {
        super(content);
    }

    @Override
    public Optional<Node> evaluate() {
        if (content.startsWith("def")) {
            var paramStart = content.indexOf('(');
            var paramEnd = content.indexOf(')');
            var parameters = Arrays.stream(content.substring(paramStart + 1, paramEnd)
                    .trim().split(","))
                    .filter(s -> !s.isBlank())
                    .map(String::trim)
                    .map(this::tokenizeParameter)
                    .collect(Collectors.toList());
            var name = content.substring(4, paramStart).trim();
            var returnIndex = content.indexOf(':', paramEnd + 1);
            var valueIndex = content.indexOf("=>");
            var trim = content.substring(returnIndex + 1, valueIndex).trim();
            var returnType = new ContentType(trim);
            var valueString = content.substring(valueIndex + 2).trim();
            var value = new ContentNode(valueString);
            return Optional.of(new Function(name, parameters, returnType, value));
        }
        return Optional.empty();
    }

    private Field tokenizeParameter(String s) {
        return new FieldTokenizer(s).evaluate()
                .orElseThrow(() -> new IllegalArgumentException("Invalid parameter syntax."));
    }
}
