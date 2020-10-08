package com.meti.compile.render.function;

import com.meti.compile.render.evaluate.AbstractNodeTokenizer;
import com.meti.compile.render.field.Field;
import com.meti.compile.render.field.FieldTokenizer;
import com.meti.compile.render.node.ContentNode;
import com.meti.compile.render.node.Node;
import com.meti.compile.render.primitive.ImplicitType;
import com.meti.compile.render.type.ContentType;
import com.meti.compile.render.type.Type;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class FunctionTokenizer extends AbstractNodeTokenizer {
    public FunctionTokenizer(String content) {
        super(content);
    }

    @Override
    public Optional<Node> evaluate() {
        if (content.startsWith("def")) {
            var parameters = tokenizeParameters();
            var name = content.substring(4, content.indexOf('(')).trim();
            var returnIndex = content.indexOf(':', content.indexOf(')') + 1);
            var returnType = tokenizeReturnType(returnIndex);
            var valueString = content.substring(content.indexOf("=>") + 2).trim();
            var value = new ContentNode(valueString);
            return Optional.of(new Function(name, parameters, returnType, value));
        }
        return Optional.empty();
    }

    private Type tokenizeReturnType(int returnIndex) {
        Type returnType;
        if (returnIndex == -1) {
            returnType = ImplicitType.ImplicitType;
        } else {
            String trim;
            trim = content.substring(returnIndex + 1, content.indexOf("=>")).trim();
            returnType = new ContentType(trim);
        }
        return returnType;
    }

    private List<Field> tokenizeParameters() {
        return extractParameters()
                .stream()
                .filter(s -> !s.isBlank())
                .map(String::trim)
                .map(this::tokenizeParameter)
                .collect(Collectors.toList());
    }

    private List<String> extractParameters() {
        if (content.contains("(") && content.contains(")")) {
            var paramStart = content.indexOf('(');
            var paramEnd = content.indexOf(')');
            var substring = content.substring(paramStart + 1, paramEnd);
            var trim = substring.trim();
            var split = trim.split(",");
            return List.of(split);
        } else if(!content.contains("(") && !content.contains(")")) {
            return Collections.emptyList();
        } else if(!content.contains("(")) {
            var format = "'%s' does not have a parameter start indicator.";
            var message = format.formatted(content);
            throw new IllegalStateException(message);
        } else {
            var format = "'%s' does not have a parameter end indicator.";
            var message = format.formatted(content);
            throw new IllegalStateException(message);
        }
    }

    private Field tokenizeParameter(String s) {
        return new FieldTokenizer(s).evaluate()
                .orElseThrow(() -> new IllegalArgumentException("Invalid parameter syntax."));
    }
}
