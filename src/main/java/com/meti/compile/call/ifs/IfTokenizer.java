package com.meti.compile.call.ifs;

import com.meti.compile.tokenize.ConditionTokenizer;
import com.meti.compile.Node;

import java.util.Optional;

public class IfTokenizer extends ConditionTokenizer {
    public IfTokenizer(String content) {
        super(content);
    }

    @Override
    public Optional<Node> tokenize() {
        if (content.startsWith("if") && content.contains("(") && content.contains(")")) {
            int start = content.indexOf('(');
            int end = findEnd(start);
            Node condition = extractCondition(start, end);
            Node value = extractValue(end);
            return Optional.of(new If_(condition, value));
        }
        return Optional.empty();
    }

}
